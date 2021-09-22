package com.example.order.service.forms;

import com.example.order.domain.model.OrderId;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PayForOrderForm {

    @NotNull
    private String orderId;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private int buildingNumber;
}
