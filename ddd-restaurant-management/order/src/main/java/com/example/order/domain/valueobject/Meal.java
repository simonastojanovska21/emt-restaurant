package com.example.order.domain.valueobject;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.valueObjects.Currency;
import com.example.sharedkernel.domain.valueObjects.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Meal implements ValueObject {
    private final MealId mealId;
    private final String mealName;
    private final Money mealPrice;

    private Meal(){
        this.mealId=MealId.randomId(MealId.class);
        this.mealName="";
        this.mealPrice=Money.valueOf(Currency.USD,0);
    }

    @JsonCreator
    public Meal(@JsonProperty("id") MealId mealId,
                @JsonProperty("mealName") String mealName,
                @JsonProperty("mealPrice") Money mealPrice){
        this.mealId=mealId;
        this.mealName=mealName;
        this.mealPrice=mealPrice;
    }
}
