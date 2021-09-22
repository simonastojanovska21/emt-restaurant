package com.example.order.xport.events;

import com.example.order.domain.model.OrderId;
import com.example.order.service.OrderService;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.events.order.OrderSuccessfullyDelivered;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrdersEventsListener {
    private final OrderService orderService;

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_SUCCESSFULLY_DELIVERED,groupId = "orders")
    public void consumeOrderSuccessfullyDelivered(String jsonMessage){
        try {
            OrderSuccessfullyDelivered event= DomainEvent.fromJson(jsonMessage,OrderSuccessfullyDelivered.class);
            this.orderService.markOrderAsDelivered(OrderId.of(event.getOrderId()));
        }
        catch (Exception e){

        }
    }

}
