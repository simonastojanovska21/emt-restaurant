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

    //vremeto na dostava na narackata
    private Instant timeForDelivery;

    //adresa za dostava na narackata
    private Address addressForDelivery;

    //id-to na narackata na koja se odnesuva dostavata
    @AttributeOverride(name = "id", column = @Column(name = "order_id", nullable = false))
    private OrderId orderForDelivery;

    //delivered atributot e postaven na false, a otkako ke se oznaci narackata kako dostavena, se postavuva na true
    private boolean delivered;

    //kreiranje na nov delivery objekt so id
    protected Delivery(){
        super(DeliveryId.randomId(DeliveryId.class));
    }

    //kreiranje na delivery object so adresa i id na naracka
    public static Delivery build(Address addressForDelivery, OrderId orderForDelivery){
        Delivery delivery=new Delivery();
        delivery.timeForDelivery=Instant.now().plus(1, ChronoUnit.HOURS);
        delivery.addressForDelivery=addressForDelivery;
        delivery.orderForDelivery=orderForDelivery;
        delivery.delivered=false;
        return delivery;
    }

    //metod koj se koristi za da se oznaci nekoja naracka deka e dostavena
    public void finishDelivery(){
        this.delivered=true;
    }
}
