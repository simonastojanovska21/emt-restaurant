package com.example.menu.xport.rest;

import com.example.menu.domain.models.Meal;
import com.example.menu.domain.models.MealId;
import com.example.menu.domain.models.MealType;
import com.example.menu.service.MealService;
import com.example.menu.service.forms.MealDto;
import com.example.menu.service.forms.MealForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/meal")
@AllArgsConstructor
public class MealRestController {

    private final MealService mealService;

    @PostMapping("/add")
    public ResponseEntity<Meal> addNewMealToMenu(@RequestBody MealForm mealForm){
        return this.mealService.addNewMealToMenu(mealForm)
                .map(meal -> ResponseEntity.ok().body(meal))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getDetailsForMeal(@PathVariable String id){
        return this.mealService.getDetailsForMeal(MealId.of(id))
                .map(meal -> ResponseEntity.ok().body(meal))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<Meal> getAllMeals(){
        return this.mealService.findAllMeals();
    }

    @GetMapping("/mealTypes")
    public List<MealType> getAllMealTypes(){
        return Arrays.asList(MealType.values());
    }

    @PostMapping("/getDetailsForMealsWithIds")
    public List<Meal> getDetailsForMealsWithIds(@RequestBody MealDto mealDto){
        return this.mealService.findAllMealsWithMealId(mealDto);
    }
}
