package com.example.sharedkernel.domain.events.meal;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

//Ovoj event se isprakja od order do meal mikroservisot, za da se izvesti deka soodvetnoto jadenje e otstraneto od naracka
@Getter
public class MealRemovedFromOrder extends DomainEvent {

    private String mealId;
    private int mealQuantity;

    public MealRemovedFromOrder(String topic) {
        super(TopicHolder.TOPIC_MEAL_REMOVED_FROM_ORDER);
    }

    public MealRemovedFromOrder(String mealId,int mealQuantity){
        super(TopicHolder.TOPIC_MEAL_REMOVED_FROM_ORDER);
        this.mealId=mealId;
        this.mealQuantity=mealQuantity;
    }

}
