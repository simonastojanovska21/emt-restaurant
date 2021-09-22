package com.example.sharedkernel.infra;

import com.example.sharedkernel.domain.events.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DomainEventPublisher {
    void publish(DomainEvent domainEvent);
}
