package com.example.order.infra;

import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.infra.DomainEventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void publish(DomainEvent domainEvent) {
        this.kafkaTemplate.send(domainEvent.topic(),domainEvent.toJson());
    }
}
