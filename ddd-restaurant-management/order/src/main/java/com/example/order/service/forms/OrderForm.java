package com.example.order.service.forms;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderForm {

    @NotNull
    private String customerUsername;
}
