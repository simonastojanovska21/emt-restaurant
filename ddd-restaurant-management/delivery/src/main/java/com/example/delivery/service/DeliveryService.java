package com.example.delivery.service;

import com.example.delivery.domain.exceptions.DeliveryIdNotFoundException;
import com.example.delivery.domain.model.Delivery;
import com.example.delivery.domain.model.DeliveryId;
import com.example.delivery.domain.valueobject.OrderId;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    Optional<Delivery> createNewDelivery(String country, String city, String street, int buildingNumber, OrderId orderId);

    List<Delivery> findAll();

    Optional<Delivery> findDeliveryById(DeliveryId deliveryId) throws DeliveryIdNotFoundException;

    Optional<Delivery> finishDelivery(DeliveryId deliveryId) throws DeliveryIdNotFoundException;
}
