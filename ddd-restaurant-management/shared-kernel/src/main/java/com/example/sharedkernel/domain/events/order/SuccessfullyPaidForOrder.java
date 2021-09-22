package com.example.sharedkernel.domain.events.order;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

//Ovoj event se isprakja do delivery microservice, so cel da se kreira delivery object za narackata koja se prosleduva
@Getter
public class SuccessfullyPaidForOrder extends DomainEvent {

    private String orderId;

    private String country;

    private String city;

    private String street;

    private int buildingNumber;

    public SuccessfullyPaidForOrder(String topic) {
        super(TopicHolder.TOPIC_SUCCESSFULLY_PAID_FOR_ORDER);
    }

    public SuccessfullyPaidForOrder(String orderId, String country, String city, String street, int buildingNumber){
        super(TopicHolder.TOPIC_SUCCESSFULLY_PAID_FOR_ORDER);
        this.orderId=orderId;
        this.country=country;
        this.city=city;
        this.street=street;
        this.buildingNumber=buildingNumber;
    }
}
