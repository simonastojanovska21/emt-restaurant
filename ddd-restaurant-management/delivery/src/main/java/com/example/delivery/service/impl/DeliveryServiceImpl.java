package com.example.delivery.service.impl;

import com.example.delivery.domain.exceptions.DeliveryIdNotFoundException;
import com.example.delivery.domain.model.Delivery;
import com.example.delivery.domain.model.DeliveryId;
import com.example.delivery.domain.repository.DeliveryRepository;
import com.example.delivery.domain.valueobject.OrderId;
import com.example.delivery.service.DeliveryService;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.order.OrderSuccessfullyDelivered;
import com.example.sharedkernel.domain.valueObjects.Address;
import com.example.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DomainEventPublisher domainEventPublisher;

    /*
    Metod koj se koristi za kreiranje na nov delivery objekt. Za sekoj objekt se cuva adresata na koja treba da se dostavi na
    narackata koja se dostavuva
     */
    @Override
    public Optional<Delivery> createNewDelivery(String country, String city, String street, int buildingNumber, OrderId orderId) {
        Address address=Address.valueOf(country,city,street,buildingNumber);
        Delivery delivery=Delivery.build(address,orderId);
        this.deliveryRepository.saveAndFlush(delivery);
        return Optional.of(delivery);
    }

    /*
    Metod koj vrakja lista na site deliveries
     */
    @Override
    public List<Delivery> findAll() {
        return this.deliveryRepository.findAll().stream().filter(each->!each.isDelivered()).collect(Collectors.toList());
    }

    /*
    Metod koj vrakja detali na delivery so soodveten id
     */
    @Override
    public Optional<Delivery> findDeliveryById(DeliveryId deliveryId) throws DeliveryIdNotFoundException {
        return this.deliveryRepository.findById(deliveryId);
    }

    /*
    So ovoj metod se oznacuva deka dostavata na narackata e zavrshena, odnosno narackata pristignala na adresata koja e vnesena
    Otkako ke se povika ovoj metod treba da se publikuva nastan do order agregatot koj ke ja oznaci narackata kako DELIVERED
    Se publikuva nastanot OrderSuccessfullyDelivered
     */
    @Override
    public Optional<Delivery> finishDelivery(DeliveryId deliveryId) throws DeliveryIdNotFoundException {
        Delivery delivery=this.deliveryRepository.findById(deliveryId).orElseThrow(DeliveryIdNotFoundException::new);
        delivery.finishDelivery();
        domainEventPublisher.publish(new OrderSuccessfullyDelivered(TopicHolder.TOPIC_ORDER_SUCCESSFULLY_DELIVERED,delivery.getOrderForDelivery().getId()));
        this.deliveryRepository.saveAndFlush(delivery);
        return Optional.of(delivery);
    }

}
