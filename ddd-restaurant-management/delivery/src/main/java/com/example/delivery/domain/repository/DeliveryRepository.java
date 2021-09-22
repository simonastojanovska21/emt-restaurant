package com.example.delivery.domain.repository;

import com.example.delivery.domain.model.Delivery;
import com.example.delivery.domain.model.DeliveryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, DeliveryId> {
}
