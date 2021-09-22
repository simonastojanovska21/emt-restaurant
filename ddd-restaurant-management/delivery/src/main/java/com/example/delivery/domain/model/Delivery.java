package com.example.delivery.domain.model;

import com.example.delivery.domain.valueobject.OrderId;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.valueObjects.Address;
import lombok.Getter;

import javax.persistence.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "delivery")
@Getter
public class Delivery extends AbstractEntity<DeliveryId> {

    private Instant timeForDelivery;

    private Address addressForDelivery;

    @AttributeOverride(name = "id", column = @Column(name = "order_id", nullable = false))
    private OrderId orderForDelivery;

    private boolean delivered;

    protected Delivery(){
        super(DeliveryId.randomId(DeliveryId.class));
    }

    public static Delivery build(Address addressForDelivery, OrderId orderForDelivery){
        Delivery delivery=new Delivery();
        delivery.timeForDelivery=Instant.now().plus(1, ChronoUnit.HOURS);
        delivery.addressForDelivery=addressForDelivery;
        delivery.orderForDelivery=orderForDelivery;
        delivery.delivered=false;
        return delivery;
    }

    public void finishDelivery(){
        this.delivered=true;
    }
}
