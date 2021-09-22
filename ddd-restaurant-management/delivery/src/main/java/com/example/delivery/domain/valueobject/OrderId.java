package com.example.delivery.domain.valueobject;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;

@Embeddable
public class OrderId extends DomainObjectId {
    protected OrderId() {
        super(OrderId.randomId(OrderId.class).getId());
    }

    public OrderId(@NonNull String uuid) {
        super(uuid);
    }

    public static OrderId of(String uuid){
        OrderId orderId=new OrderId(uuid);
        return orderId;
    }
}
