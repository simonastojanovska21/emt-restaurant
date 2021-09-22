package com.example.menu.service.impl;

import com.example.menu.domain.exceptions.MealIdNotFoundException;
import com.example.menu.domain.models.Meal;
import com.example.menu.domain.models.MealId;
import com.example.menu.domain.repository.MealRepository;
import com.example.menu.domain.valueobjects.Ingredient;
import com.example.menu.service.MealService;
import com.example.menu.service.forms.MealDto;
import com.example.menu.service.forms.MealForm;
import com.example.sharedkernel.domain.valueObjects.Currency;
import com.example.sharedkernel.domain.valueObjects.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final Validator validator;

    @Override
    public Optional<Meal> addNewMealToMenu(MealForm mealForm) {
        Objects.requireNonNull(mealForm,"meal form should not be null");
        var constraintViolations = validator.validate(mealForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        Meal meal=toDomainObject(mealForm);
        this.mealRepository.saveAndFlush(meal);
        return Optional.of(meal);
    }

    @Override
    public Optional<Meal> getDetailsForMeal(MealId id) throws MealIdNotFoundException {
        return this.mealRepository.findById(id);
    }

    @Override
    public List<Meal> findAllMeals() {
        return this.mealRepository.findAll();
    }

    @Override
    public List<Meal> findAllMealsWithMealId(MealDto mealDto) throws MealIdNotFoundException {
        List<MealId> mealIdList=mealDto.getIds().stream().map(MealId::of).collect(Collectors.toList());
        return this.mealRepository.findAllById(mealIdList);
    }

    @Override
    public Optional<Meal> mealAddedInOrder(MealId mealId, int number) throws MealIdNotFoundException {
        Meal meal=this.mealRepository.findById(mealId).orElseThrow(MealIdNotFoundException::new);
        meal.addOrdersForMeal(number);
        this.mealRepository.saveAndFlush(meal);
        return Optional.of(meal);
    }

    @Override
    public Optional<Meal> mealRemovedFromOrder(MealId mealId, int number) throws MealIdNotFoundException {
        Meal meal=this.mealRepository.findById(mealId).orElseThrow(MealIdNotFoundException::new);
        meal.removeOrdersForMeal(number);
        this.mealRepository.saveAndFlush(meal);
        return Optional.of(meal);
    }

    private Meal toDomainObject(MealForm mealForm){
        Money mealPrice=new Money(Currency.USD, mealForm.getMealPrice());
        Ingredient ingredientsForMeal=new Ingredient(mealForm.getIngredientsForMeal());
        Meal meal=Meal.build(mealForm.getMealName(),mealForm.getMealDescription(),mealPrice,
                mealForm.getMealType(),ingredientsForMeal,mealForm.getImageUrl());
        return meal;
    }
}
