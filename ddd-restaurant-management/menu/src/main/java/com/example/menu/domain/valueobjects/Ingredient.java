package com.example.menu.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Vrednosen objekt koj se koristi za prikaz na sostojkite na sekoe jadenje.
@Embeddable
@Getter
public class Ingredient implements ValueObject {

    private final String ingredientsList;

    protected Ingredient(){
        this.ingredientsList="";
    }

    public Ingredient(String ingredients){
        this.ingredientsList = ingredients;
    }

    public static Ingredient valueOf(String ingredients){
        return new Ingredient(ingredients);
    }
}
