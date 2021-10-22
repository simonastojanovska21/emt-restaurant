package com.example.delivery.xport.events;

import com.example.delivery.domain.valueobject.OrderId;
import com.example.delivery.service.DeliveryService;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.events.order.SuccessfullyPaidForOrder;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//Servis koj slusha za publikuvanje na SuccessfullyPaidForOrder nastanot od order mikroservisot
//otkako ke se publikuva ovoj nastan se kreira nova naracka so potrebnite podatoci
@Service
@AllArgsConstructor
public class DeliveriesEventListener {

    private final DeliveryService deliveryService;

    @KafkaListener(topics = TopicHolder.TOPIC_SUCCESSFULLY_PAID_FOR_ORDER,groupId = "mealDeliveries")
    public void consumeSuccessfullyPayedForOrder(String jsonMessage){
        try {
            SuccessfullyPaidForOrder event= DomainEvent.fromJson(jsonMessage, SuccessfullyPaidForOrder.class);
            this.deliveryService.createNewDelivery(event.getCountry(),event.getCity(),event.getStreet(),event.getBuildingNumber(),OrderId.of(event.getOrderId()));
        }
        catch (Exception e){

        }
    }
}
