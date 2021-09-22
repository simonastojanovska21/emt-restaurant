package com.example.order.domain.model;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class OrderItemId extends DomainObjectId {
    private OrderItemId() {
        super(OrderItemId.randomId(OrderItemId.class).getId());
    }

    public OrderItemId(@NonNull String uuid) {
        super(uuid);
    }

    public static OrderItemId of(String uuid){
        OrderItemId orderItemId=new OrderItemId(uuid);
        return orderItemId;
    }
}
