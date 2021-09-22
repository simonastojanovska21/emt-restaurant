package com.example.delivery.domain.config;


import com.example.delivery.domain.model.Delivery;
import com.example.delivery.domain.repository.DeliveryRepository;
import com.example.delivery.domain.valueobject.OrderId;
import com.example.sharedkernel.domain.valueObjects.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final DeliveryRepository deliveryRepository;

    @PostConstruct
    public void initData(){
        Address address=Address.valueOf("Makedonija","Skopje","Ulica 11",12);
        Delivery delivery=Delivery.build(address, OrderId.of("052ce40e-970c-4e06-9aff-7014ee6dfd88"));
    }
}
