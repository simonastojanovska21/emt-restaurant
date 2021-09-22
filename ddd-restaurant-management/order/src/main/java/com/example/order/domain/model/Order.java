package com.example.order.domain.model;

import com.example.order.domain.valueobject.CustomerUsername;
import com.example.order.domain.valueobject.Meal;
import com.example.order.domain.valueobject.MealId;
import com.example.order.domain.valueobject.Quantity;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.valueObjects.Currency;
import com.example.sharedkernel.domain.valueObjects.Money;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    private Instant orderedOn;

    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> orderedItemsInOrder = new HashSet<>();

    @AttributeOverride(name = "username", column = @Column(name = "customer_username", nullable = false))
    private CustomerUsername CustomerUsername;

    protected Order(){
        super(OrderId.randomId(OrderId.class));
    }

    public Order(Instant now,Currency currency, CustomerUsername customerUsername){
        super(OrderId.randomId(OrderId.class));
        this.orderedOn=now;
        this.currency=currency;
        orderStatus=OrderStatus.ACTIVE;
        this.CustomerUsername=customerUsername;
    }

    public static Order build(Instant now,Currency currency, CustomerUsername customerUsername){
        Order order=new Order();
        order.orderedOn=now;
        order.currency=currency;
        order.CustomerUsername=customerUsername;
        return order;
    }

    //Metod so koj se menuva statusot na narackata, vo Delivering, Canceled i slicno;
    public void changeOrderStatus(OrderStatus orderStatus){
        this.orderStatus=orderStatus;
    }

    public Money orderTotal(){
        return orderedItemsInOrder.stream().map(OrderItem::subtotal).reduce(new Money(currency,0),Money::add);
    }

    //metod koj se koristi za dodavanje na order item vo narackata
    public OrderItem addItemInOrder(@NonNull MealId mealId, @NonNull Money mealPrice, Quantity quantity){
        Objects.requireNonNull(mealId,"meal must not be null");
        var item  = new OrderItem(mealId,mealPrice,quantity);
        orderedItemsInOrder.add(item);
        return item;
    }

    //metod so koj otstranuvame order item od narackata
    public void removeItemFromOrder(@NonNull OrderItemId orderItemId){
        Objects.requireNonNull(orderItemId,"Order Item must not be null");
        orderedItemsInOrder.removeIf(v->v.getId().equals(orderItemId));
    }

    //otkako korisnikot ke go dodade order item vo narackata toj ke moze da ja menuva kolicinata na order item
    //za taa cel se koristat ovie dva metodi

    //metod koj se koristi za zgolemuvanje na kolicinata na order item za eden vo narackata
    public OrderItem addOrderItemQuantity(@NonNull OrderItemId orderItemId){
        Objects.requireNonNull(orderItemId,"Order Item must not be null");
        for (OrderItem item: orderedItemsInOrder) {
            if(item.getId().equals(orderItemId)){
                item.addQuantity();
                return item;
            }
        }
        return null;
    }

    //metod koj se koristi za namaluvanje na kolicinata na order item za eden vo narackata
    public OrderItem removeOrderItemQuantity(@NonNull OrderItemId orderItemId){
        Objects.requireNonNull(orderItemId,"Order Item must not be null");
        for (OrderItem item: orderedItemsInOrder) {
            if(item.getId().equals(orderItemId)){
                item.removeQuantity();
                return item;
            }
        }
        return null;
    }

    //metod so koj zemame order item quantity za soodveten order item. Ovoj metod se koristi za da se namali brojot na naracki na nekoe
    //jadenje pri brishenje na order item od listata
    public Quantity getQuantityForOrderItem(OrderItemId orderItemId){
        return orderedItemsInOrder.stream().filter(each->each.getId().equals(orderItemId)).findFirst().get().getOrderItemQuantity();
    }

    public MealId getMealIdForOrderItem(OrderItemId orderItemId){
        return orderedItemsInOrder.stream().filter(each->each.getId().equals(orderItemId)).findFirst().get().getMealId();
    }
}
