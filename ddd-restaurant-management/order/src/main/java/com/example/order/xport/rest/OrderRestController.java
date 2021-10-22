package com.example.order.xport.rest;

import com.example.order.domain.model.Order;
import com.example.order.domain.model.OrderId;
import com.example.order.service.OrderService;
import com.example.order.service.forms.OrderForm;
import com.example.order.service.forms.OrderItemForm;
import com.example.order.service.forms.OrderOrderItemForm;
import com.example.order.service.forms.PayForOrderForm;
import com.example.sharedkernel.domain.valueObjects.Money;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Rest kontoler koj se koristi od frontend aplikacijata
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("/add")
    public OrderId createNewOrder(@RequestBody OrderForm orderForm){
        return this.orderService.createNewOrder(orderForm);
    }

    @GetMapping("/all")
    public List<Order> findAllOrders(){
        return this.orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable String id)
    {
        return this.orderService.findOrderById(OrderId.of(id))
                .map(order -> ResponseEntity.ok().body(order))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/total/{id}")
    public Money getTotalForOrder(@PathVariable String id){
        return this.orderService.getTotalForOrder(OrderId.of(id));
    }

    @PostMapping("/payForOrder")
    public ResponseEntity<Order> payForOrder(@RequestBody PayForOrderForm payForOrderForm){
        return this.orderService.payForOrder(payForOrderForm)
                .map(order -> ResponseEntity.ok().body(order))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<Order> cancelOrder(@PathVariable String id){
        return this.orderService.cancelOrder(OrderId.of(id))
                .map(order -> ResponseEntity.ok().body(order))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/orderForUser/{username}")
    public ResponseEntity<Order> getOrderForUser(@PathVariable String username){
        return this.orderService.getOrderForUser(username)
                .map(order -> ResponseEntity.ok().body(order))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/addOrderItemInOrder")
    public void addOrderItemInOrder(@RequestBody OrderItemForm orderItemForm){
        this.orderService.addOrderItemInOrder(orderItemForm);
    }

    @PostMapping("/deleteOrderItemFromOrder")
    public void deleteOrderItemFromOrder(@RequestBody OrderOrderItemForm orderOrderItemForm)
    {
        this.orderService.deleteOrderItemFromOrder(orderOrderItemForm);
    }

    @PostMapping("/addOrderItemQuantity")
    public void addOrderItemQuantity(@RequestBody OrderOrderItemForm orderOrderItemForm)
    {
        this.orderService.addOrderItemQuantity(orderOrderItemForm);
    }

    @PostMapping("/removeOrderItemQuantity")
    public void removeOrderItemQuantity(@RequestBody OrderOrderItemForm orderOrderItemForm)
    {
        this.orderService.removeOrderItemQuantity(orderOrderItemForm);
    }

}
