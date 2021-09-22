package com.example.delivery.service.forms;

import com.example.delivery.domain.valueobject.OrderId;
import com.example.sharedkernel.domain.valueObjects.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
public class DeliveryForm {
    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private int buildingNumber;

    @NotNull
    private OrderId orderForDelivery;

}
