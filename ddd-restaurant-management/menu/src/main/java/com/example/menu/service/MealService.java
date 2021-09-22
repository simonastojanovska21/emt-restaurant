package com.example.menu.service;

import com.example.menu.domain.exceptions.MealIdNotFoundException;
import com.example.menu.domain.models.Meal;
import com.example.menu.domain.models.MealId;
import com.example.menu.service.forms.MealDto;
import com.example.menu.service.forms.MealForm;

import java.util.List;
import java.util.Optional;

public interface MealService {

    Optional<Meal> addNewMealToMenu(MealForm mealForm);

    Optional<Meal> getDetailsForMeal(MealId id) throws MealIdNotFoundException;

    List<Meal> findAllMeals();

    List<Meal> findAllMealsWithMealId(MealDto mealDto) throws MealIdNotFoundException;

    Optional<Meal> mealAddedInOrder(MealId mealId, int number) throws MealIdNotFoundException;

    Optional<Meal> mealRemovedFromOrder(MealId mealId,int number) throws MealIdNotFoundException;

}
