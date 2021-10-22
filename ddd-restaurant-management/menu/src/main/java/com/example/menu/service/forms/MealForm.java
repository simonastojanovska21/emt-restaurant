package com.example.menu.service.forms;


import com.example.menu.domain.models.MealType;
import com.example.menu.domain.valueobjects.Ingredient;
import com.example.sharedkernel.domain.valueObjects.Money;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//forma koja se koristi pri dodavanje na jadenje
@Data
public class MealForm {

    @NotNull
    private String mealName;

    @NotNull
    private String mealDescription;

    @NotNull
    @Min(1)
    private double mealPrice;

    @NotNull
    private MealType mealType;

    @NotNull
    private String ingredientsForMeal;

    @NotNull
    private String imageUrl;
}
