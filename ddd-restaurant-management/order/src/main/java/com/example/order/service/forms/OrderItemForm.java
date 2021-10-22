package com.example.order.service.forms;


import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

//Za sekoj order item se prakjaat informacii za jadenjeto koe se naracuva (id, ime i cena) i kolicinata koja se naracuva
//kako i narackata na koja se odnesuva
@Data
public class OrderItemForm {

    @NotNull
    private String orderId;

    @NotNull
    private String mealId;

    @NotNull
    private double mealPrice;

    private int mealQuantity;
}
