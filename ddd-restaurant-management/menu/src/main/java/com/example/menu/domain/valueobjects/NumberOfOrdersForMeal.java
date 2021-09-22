package com.example.menu.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;


import javax.persistence.Embeddable;

//Vrednosen objekt koj za sekoe jadenje go prikazuva brojot na naracki. Potocno oznacuva kolku porcii treba da se podgotvat od
//sekoe jadenje. Kako korisnicite dodavaat/odzemaat jadenja od narackata taka ovoj broj se menuva.
//Otkako nekoe jadenje ke bide podgotveno vrabotenite ke moze da go namalat brojot na naracki.
@Embeddable
@Getter
public class NumberOfOrdersForMeal implements ValueObject {

    private final int numberOfOrdersForMeal;

    public NumberOfOrdersForMeal(){
        this.numberOfOrdersForMeal=0;
    }

    public NumberOfOrdersForMeal(int numberOfOrdersForMeal){
        this.numberOfOrdersForMeal=numberOfOrdersForMeal;
    }

    public static NumberOfOrdersForMeal valueOf(int numberOfOrdersForMeal){
        return new NumberOfOrdersForMeal(numberOfOrdersForMeal);
    }

    public NumberOfOrdersForMeal addNumberOfOrdersForMeal(int number){
        return new NumberOfOrdersForMeal(numberOfOrdersForMeal+number);
    }

    public NumberOfOrdersForMeal removeNumberOfOrdersForMeal(int number){
        return new NumberOfOrdersForMeal(numberOfOrdersForMeal-number);
    }
}
