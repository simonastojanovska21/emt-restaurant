package com.example.order.service;

import com.example.order.domain.exceptions.OrderIdNotFoundException;
import com.example.order.domain.exceptions.OrderItemIdNotFoundException;
import com.example.order.domain.model.Order;
import com.example.order.domain.model.OrderId;
import com.example.order.domain.model.OrderStatus;
import com.example.order.service.forms.OrderForm;
import com.example.order.service.forms.OrderItemForm;
import com.example.order.service.forms.OrderOrderItemForm;
import com.example.order.service.forms.PayForOrderForm;
import com.example.sharedkernel.domain.valueObjects.Money;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderId createNewOrder(OrderForm orderForm);

    List<Order> findAllOrders();

    Optional<Order> findOrderById(OrderId id);

    Money getTotalForOrder(OrderId orderId) throws OrderIdNotFoundException;

    Optional<Order> payForOrder(PayForOrderForm payForOrderForm) throws OrderIdNotFoundException;

    Optional<Order> cancelOrder(OrderId orderId) throws OrderIdNotFoundException;

    void markOrderAsDelivered(OrderId orderId) throws OrderIdNotFoundException;

    void changeOrderStatus(OrderId orderId, OrderStatus orderStatus) throws OrderIdNotFoundException;

    Optional<Order> getOrderForUser(String username);

    void addOrderItemInOrder(OrderItemForm orderItemForm) throws OrderIdNotFoundException, OrderItemIdNotFoundException;;

    void deleteOrderItemFromOrder(OrderOrderItemForm orderOrderItemForm) throws OrderIdNotFoundException, OrderItemIdNotFoundException;

    void addOrderItemQuantity(OrderOrderItemForm orderOrderItemForm) throws OrderIdNotFoundException, OrderItemIdNotFoundException;

    void removeOrderItemQuantity(OrderOrderItemForm orderOrderItemForm) throws OrderIdNotFoundException, OrderItemIdNotFoundException;


}
