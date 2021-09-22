package com.example.order.service.forms;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class OrderOrderItemForm {

    @NotNull
    private String orderId;

    @NotNull
    private String orderItemId;
}
