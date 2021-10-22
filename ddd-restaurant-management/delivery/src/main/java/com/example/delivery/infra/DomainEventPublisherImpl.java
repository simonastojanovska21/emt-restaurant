package com.example.delivery.infra;

import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.infra.DomainEventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//Publisher na domain event od delivery mikroservisot
//se publikuva nastanot OrderSuccessfullyDelivered do order mikroservisot so cel da se smeni
// statusot na narackata vo DELIVERD
@Service
@AllArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void publish(DomainEvent domainEvent) {
        this.kafkaTemplate.send(domainEvent.topic(),domainEvent.toJson());
    }
}
