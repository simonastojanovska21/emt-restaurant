package com.example.order.service.impl;

import com.example.order.domain.exceptions.OrderIdNotFoundException;
import com.example.order.domain.exceptions.OrderItemIdNotFoundException;
import com.example.order.domain.model.Order;
import com.example.order.domain.model.OrderId;
import com.example.order.domain.model.OrderItemId;
import com.example.order.domain.model.OrderStatus;
import com.example.order.domain.repository.OrderRepository;
import com.example.order.domain.valueobject.CustomerUsername;
import com.example.order.domain.valueobject.MealId;
import com.example.order.domain.valueobject.Quantity;
import com.example.order.service.OrderService;
import com.example.order.service.forms.OrderForm;
import com.example.order.service.forms.OrderItemForm;
import com.example.order.service.forms.OrderOrderItemForm;
import com.example.order.service.forms.PayForOrderForm;
import com.example.sharedkernel.domain.events.meal.MealAddedInOrder;
import com.example.sharedkernel.domain.events.meal.MealRemovedFromOrder;
import com.example.sharedkernel.domain.events.order.SuccessfullyPaidForOrder;
import com.example.sharedkernel.domain.valueObjects.Currency;
import com.example.sharedkernel.domain.valueObjects.Money;
import com.example.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
//aplikaciski servis
@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;


    /*
    Metod koj se koristi za kreiranje na nova naracka. Pri klik na kopceto Kreiraj naracka se kraira naracka
    za korisnikot so soodvetno username.datum na kreiranje e toj moment od vremeto, default valuta e dolari
    a inicijalno nema order items. kako korisnikot go razgleduva menito taka dodava order items vo narackata
     */
    @Override
    public OrderId createNewOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder=orderRepository.saveAndFlush(toDomainObject(orderForm));
        return newOrder.getId();
    }

    /*
    Metod koj gi vrakja site naracki
     */
    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    /*
    metod koj vrakja detali za nekoja naracka so soodveten orderID
     */
    @Override
    public Optional<Order> findOrderById(OrderId id) {
        return orderRepository.findById(id);
    }

    /*
    metod koj ja vrakja cenata na narackata
     */
    @Override
    public Money getTotalForOrder(OrderId orderId) {
        Order order=this.orderRepository.findById(orderId).orElseThrow(OrderIdNotFoundException::new);
        return order.orderTotal();
    }

    /*
    Metod so koj se vrshi plakjane na naracka. Otkako ke se plati narackata treba da se kreira nov delivery object
    Za taa cel treba da se publikuva event kon delivery microservice, SuccessfullyPayedForOrder, a stausot na narackata se menuva
    vo PAID
     */
    @SneakyThrows
    @Override
    public Optional<Order> payForOrder(PayForOrderForm payForOrderForm) throws OrderIdNotFoundException {
        domainEventPublisher.publish(new SuccessfullyPaidForOrder(payForOrderForm.getOrderId(),
                payForOrderForm.getCountry(),payForOrderForm.getCity(),payForOrderForm.getStreet(),payForOrderForm.getBuildingNumber()));
        Order order=this.orderRepository.findById(OrderId.of(payForOrderForm.getOrderId())).orElseThrow(OrderIdNotFoundException::new);
        order.changeOrderStatus(OrderStatus.PAID);
        orderRepository.saveAndFlush(order);
        return Optional.of(order);
    }

    /*
    Metod so koj narackata na korisnikot se oznacuva kako otkazana.
    Treba da se isprati event do meal za namaluvanje na broj na naracki na sekoj order item od narackata
     */

    @Override
    public Optional<Order> cancelOrder(OrderId orderId) throws OrderIdNotFoundException {
        Order order=this.orderRepository.findById(orderId).orElseThrow(OrderIdNotFoundException::new);
        order.getOrderedItemsInOrder().forEach(each->domainEventPublisher.publish(new MealRemovedFromOrder(each.getMealId().getId(),each.getOrderItemQuantity().getQuantity())));
        order.changeOrderStatus(OrderStatus.CANCELED);
        orderRepository.saveAndFlush(order);
        return Optional.of(order);
    }

    /*
    Koga narackata ke bide dostavena do korisnikot istata treba da ima status Delivered
     */
    @Override
    public void markOrderAsDelivered(OrderId orderId) throws OrderIdNotFoundException {
        this.changeOrderStatus(orderId, OrderStatus.DELIVERED);
    }

    /*
    Metod koj se koristi za menuvanje na statusot na narackata. Narackata moze da premine od ACTIVE vo PAID, od ACTIVE vo CANCELED
    i od PAID vo DELIVERED
     */
    @Override
    public void changeOrderStatus(OrderId orderId, OrderStatus orderStatus) throws OrderIdNotFoundException {
        Order order=this.orderRepository.findById(orderId).orElseThrow(OrderIdNotFoundException::new);
        order.changeOrderStatus(orderStatus);
        orderRepository.saveAndFlush(order);
    }

    /*
    metod so koj ja zemame aktivnata naracka za korisnikot
     */
    @Override
    public Optional<Order> getOrderForUser(String username) {
        CustomerUsername customerUsername=new CustomerUsername(username);
        return this.orderRepository.findAll().stream().filter(each->each.getCustomerUsername().equals(customerUsername) && each.getOrderStatus().equals(OrderStatus.ACTIVE)).findFirst();
    }

    /*
    metod so koj se dodava order item vo narackata. Kako dodavame order item vo narackata treba da se izvesti meal
    agregatot deka brojt na naraci e zgolemen za orderItemForm.getMealQuantity(). Za taa cel se publikuva nastanot
    MealAddedInOrder
     */
    @SneakyThrows
    @Override
    public void addOrderItemInOrder(OrderItemForm orderItemForm) throws OrderIdNotFoundException {
        Order order=this.orderRepository.findById(OrderId.of(orderItemForm.getOrderId())).orElseThrow(OrderIdNotFoundException::new);
        Money mealPrice=new Money(Currency.USD,orderItemForm.getMealPrice());
        Quantity mealQuantity=new Quantity(orderItemForm.getMealQuantity());
        order.addItemInOrder(MealId.of(orderItemForm.getMealId()),mealPrice,mealQuantity);
        domainEventPublisher.publish(new MealAddedInOrder(orderItemForm.getMealId(),orderItemForm.getMealQuantity()));
        this.orderRepository.saveAndFlush(order);
    }

    /*
    metod so koj brisheme order item od narackata. Kako brisheme brojt na naracki vo meal agregatot treba da se namali
    Se publikuva MealRemovedFromOrder nastanot
     */
    @SneakyThrows
    @Override
    public void deleteOrderItemFromOrder(OrderOrderItemForm orderOrderItemForm) throws OrderIdNotFoundException, OrderItemIdNotFoundException {
        Order order=this.orderRepository.findById(OrderId.of(orderOrderItemForm.getOrderId())).orElseThrow(OrderIdNotFoundException::new);
        Quantity quantity=order.getQuantityForOrderItem(OrderItemId.of(orderOrderItemForm.getOrderItemId()));
        domainEventPublisher.publish(new MealRemovedFromOrder(order.getMealIdForOrderItem(OrderItemId.of(orderOrderItemForm.getOrderItemId())).getId(), quantity.getQuantity()));
        order.removeItemFromOrder(OrderItemId.of(orderOrderItemForm.getOrderItemId()));
        this.orderRepository.saveAndFlush(order);
    }

    /*
    so ovoj metod kolicinata na order item se zgolemuva za eden
    Isto taka se publikuva MealAddedInOrder so quantity=1;
     */
    @SneakyThrows
    @Override
    public void addOrderItemQuantity(OrderOrderItemForm orderOrderItemForm) throws OrderIdNotFoundException, OrderItemIdNotFoundException {
        Order order=this.orderRepository.findById(OrderId.of(orderOrderItemForm.getOrderId())).orElseThrow(OrderIdNotFoundException::new);
        order.addOrderItemQuantity(OrderItemId.of(orderOrderItemForm.getOrderItemId()));
        domainEventPublisher.publish(new MealAddedInOrder(order.getMealIdForOrderItem(OrderItemId.of(orderOrderItemForm.getOrderItemId())).getId(),1));
        this.orderRepository.saveAndFlush(order);
    }

    /*
    so ovoj metod kolicinata na order item se namaluva za eden
    Isto taka se Publikuva MealRemovedFromOrder so quantity=1
     */
    @SneakyThrows
    @Override
    public void removeOrderItemQuantity(OrderOrderItemForm orderOrderItemForm) throws OrderIdNotFoundException, OrderItemIdNotFoundException {
        Order order=this.orderRepository.findById(OrderId.of(orderOrderItemForm.getOrderId())).orElseThrow(OrderIdNotFoundException::new);
        domainEventPublisher.publish(new MealRemovedFromOrder(order.getMealIdForOrderItem(OrderItemId.of(orderOrderItemForm.getOrderItemId())).getId(),1));
        order.removeOrderItemQuantity(OrderItemId.of(orderOrderItemForm.getOrderItemId()));
        this.orderRepository.saveAndFlush(order);
    }

    private Order toDomainObject(OrderForm orderForm){
        CustomerUsername customerUsername=new CustomerUsername(orderForm.getCustomerUsername());
        return new Order(Instant.now(), Currency.USD,customerUsername);
    }
}
