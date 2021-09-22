package com.example.sharedkernel.domain.events.order;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

//Ovoj event se isprakja od delivery microservice do order microservice za da se izvesti deka narackata e dostavena i da se smeni
//nejziniot status vo Delivered
@Getter
public class OrderSuccessfullyDelivered extends DomainEvent {

    private String orderId;

    public OrderSuccessfullyDelivered(String topic) {
        super(TopicHolder.TOPIC_ORDER_SUCCESSFULLY_DELIVERED);
    }

    public OrderSuccessfullyDelivered(String topic, String orderId){
        super(TopicHolder.TOPIC_ORDER_SUCCESSFULLY_DELIVERED);
        this.orderId=orderId;
    }
}
