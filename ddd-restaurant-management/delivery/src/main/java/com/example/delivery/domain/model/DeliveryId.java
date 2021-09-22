package com.example.delivery.domain.model;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;


public class DeliveryId extends DomainObjectId {

    private DeliveryId() {
        super(DeliveryId.randomId(DeliveryId.class).getId());
    }

    public DeliveryId(@NonNull String uuid) {
        super(uuid);
    }

    public static DeliveryId of(String uuid){
        DeliveryId deliveryId=new DeliveryId(uuid);
        return deliveryId;
    }
}
