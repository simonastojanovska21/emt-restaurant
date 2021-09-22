package com.example.menu.domain.models;

import com.example.menu.domain.valueobjects.Ingredient;
import com.example.menu.domain.valueobjects.NumberOfOrdersForMeal;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.valueObjects.Money;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meal")
@Getter
public class Meal extends AbstractEntity<MealId> {

    private String mealName;

    private String mealDescription;

    private Money mealPrice;

    private MealType mealType;

    private Ingredient ingredientsForMeal;

    private NumberOfOrdersForMeal numberOfOrdersForMeal;

    private String imageUrl;

    protected Meal(){
        super(MealId.randomId(MealId.class));
    }

    public static Meal build(String mealName,String mealDescription,Money mealPrice, MealType mealType,Ingredient ingredientsForMeal,String imageUrl){
        Meal meal=new Meal();
        meal.mealName=mealName;
        meal.mealDescription=mealDescription;
        meal.mealPrice=mealPrice;
        meal.mealType=mealType;
        meal.ingredientsForMeal=ingredientsForMeal;
        meal.numberOfOrdersForMeal=new NumberOfOrdersForMeal();
        meal.imageUrl=imageUrl;

        return meal;
    }

    //Za sekoe jadenje go cuvame brojot na naracki. kako korisnicite go dodavaat jadenjeto vo svojata naracki
    //brojt na naracki za jadenjeto se zgolemuva. So ova vrabotenite bi dobile informacija za kolicinata na jadenja koja treba da ja podgotvat
    public void addOrdersForMeal(int number){
        this.numberOfOrdersForMeal=numberOfOrdersForMeal.addNumberOfOrdersForMeal(number);
    }

    //Koga korisnicite ke go izbrishat jadenjeto od narackata ili ke ja otkazat narackata brojot na naracki se namaluva
    public void removeOrdersForMeal(int number){
        this.numberOfOrdersForMeal=numberOfOrdersForMeal.removeNumberOfOrdersForMeal(number);
    }



}
