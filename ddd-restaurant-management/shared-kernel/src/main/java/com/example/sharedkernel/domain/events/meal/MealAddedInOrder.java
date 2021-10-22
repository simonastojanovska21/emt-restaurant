package com.example.sharedkernel.domain.events.meal;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

//Ovoj event se isprakja od order do meal mikroservisot, za da se izvesti deka soodvetnoto jadenje e dodadeno vo naracka
@Getter
public class MealAddedInOrder extends DomainEvent {

    private String mealId;
    private int mealQuantity;

    public MealAddedInOrder(String topic) {
        super(TopicHolder.TOPIC_MEAL_ADDED_IN_ORDER);
    }

    public MealAddedInOrder(String mealId,int mealQuantity){
        super(TopicHolder.TOPIC_MEAL_ADDED_IN_ORDER);
        this.mealId=mealId;
        this.mealQuantity=mealQuantity;
    }
}
