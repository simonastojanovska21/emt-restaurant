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

    /*
    metod koj se koristi za dodavanje na novo jadenje vo menito
     */
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

    /*
    metod koj se koristi za prevzemanje detali za nekoe jadenje
     */
    @Override
    public Optional<Meal> getDetailsForMeal(MealId id) throws MealIdNotFoundException {
        return this.mealRepository.findById(id);
    }

    /*
    metod so koj zemame lista od site jadenja vo menito
     */
    @Override
    public List<Meal> findAllMeals() {
        return this.mealRepository.findAll();
    }

    /*
    metod koj se koristi sekogash koga jadenjeto ke se dodade vo nekoja naracka, se povikuva
    metod od Meal klasata so koj brojot na naracki za jadenjeto se zgolemuva
     */
    @Override
    public void mealAddedInOrder(MealId mealId, int number) throws MealIdNotFoundException {
        Meal meal=this.mealRepository.findById(mealId).orElseThrow(MealIdNotFoundException::new);
        meal.addOrdersForMeal(number);
        this.mealRepository.saveAndFlush(meal);
    }

    /*
    metod koj se koristi sekogash koga jadenjeto ke se otstrani od nekoja naracka, se povikuva
    metod od Meal klasata so koj brojot na naracki za jadenjeto se namaluva
     */
    @Override
    public void mealRemovedFromOrder(MealId mealId, int number) throws MealIdNotFoundException {
        Meal meal=this.mealRepository.findById(mealId).orElseThrow(MealIdNotFoundException::new);
        meal.removeOrdersForMeal(number);
        this.mealRepository.saveAndFlush(meal);
    }

    /*
    private metod koj se koristi za kreiranje na Meal od MealForm objekt
     */
    private Meal toDomainObject(MealForm mealForm){
        Money mealPrice=new Money(Currency.USD, mealForm.getMealPrice());
        Ingredient ingredientsForMeal=new Ingredient(mealForm.getIngredientsForMeal());
        return Meal.build(mealForm.getMealName(),mealForm.getMealDescription(),mealPrice,
                mealForm.getMealType(),ingredientsForMeal,mealForm.getImageUrl());
    }
}
