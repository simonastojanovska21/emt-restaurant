package com.example.menu.xport.events;

import com.example.menu.domain.models.MealId;
import com.example.menu.service.MealService;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.events.meal.MealAddedInOrder;
import com.example.sharedkernel.domain.events.meal.MealRemovedFromOrder;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//Servis koj slusha za publikuvanje na nastanite MealAddedInOrder i MealRemovedFromOrder od order mikroservisot
//otkako ke se publikuva nekoj od ovie nastanit se povukuva soodvetniot metod od servisnata logika
@Service
@AllArgsConstructor
public class MealEventListener {

    private final MealService mealService;

    @KafkaListener(topics = TopicHolder.TOPIC_MEAL_ADDED_IN_ORDER,groupId = "mealDeliveries")
    public void consumeMealAddedInOrder(String jsonMessage){
        try{
            MealAddedInOrder event= DomainEvent.fromJson(jsonMessage,MealAddedInOrder.class);
            this.mealService.mealAddedInOrder(MealId.of(event.getMealId()),event.getMealQuantity());
        }
        catch (Exception e){

        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_MEAL_REMOVED_FROM_ORDER,groupId = "mealDeliveries")
    public void consumeMealRemovedFromOrder(String jsonMessage){
        try{
            MealRemovedFromOrder event=DomainEvent.fromJson(jsonMessage,MealRemovedFromOrder.class);
            mealService.mealRemovedFromOrder(MealId.of(event.getMealId()),event.getMealQuantity());
        }
        catch (Exception e){

        }
    }
}
