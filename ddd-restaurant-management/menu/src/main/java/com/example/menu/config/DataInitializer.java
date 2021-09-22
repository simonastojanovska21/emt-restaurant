package com.example.menu.config;

import com.example.menu.domain.models.Meal;
import com.example.menu.domain.models.MealType;
import com.example.menu.domain.repository.MealRepository;
import com.example.menu.domain.valueobjects.Ingredient;
import com.example.sharedkernel.domain.valueObjects.Currency;
import com.example.sharedkernel.domain.valueObjects.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final MealRepository mealRepository;

    @PostConstruct
    public void initData(){
        Meal meal1=Meal.build("Meal1","Meal1 description", Money.valueOf(Currency.USD,50),
                MealType.Pasta, Ingredient.valueOf("Cheese,Bacon,Ketchup,Spaghetti"),"https://www.bosscaffe.com/sites/default/files/styles/product_thumb/public/2019-04/PASTA_BOLOGNESE.jpg?itok=xlPQ0A54");

        Meal meal9=Meal.build("Meal9","Meal9 description",Money.valueOf(Currency.USD,32),
                MealType.Pasta,Ingredient.valueOf("Mushrooms,Ketchup,Macaroni,Shrimps"),"https://taste.co.za/wp-content/uploads/2015/03/fiorelli-pasta-with-smoked-salmon-and-cavi-art-3650-400x400.jpg");

        Meal meal3=Meal.build("Meal3","Meal3 description",Money.valueOf(Currency.USD,32),
                MealType.Seafood,Ingredient.valueOf("Pepper,Onion,Lettuce,Salmon,Shrimps"),"https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15548.jpg?ext=.jpg");

        Meal meal4=Meal.build("Meal4","Meal4 description", Money.valueOf(Currency.USD,12),
                MealType.Pizza,Ingredient.valueOf("Cheese,Pepperoni,Mushrooms,Mozzarella,Ketchup,Tomato"),"https://www.woolwichdairy.com/-/media/ecosystem/divisions/canada-dairy/sites/woolwich-dairy/woolwich-dairy-images/recipes/pinterest/classic-veggie-pizza-400x400.ashx?revision=edb57bc9-56e4-4a68-9624-ee641b9d13ed");

        Meal meal6=Meal.build("Meal6","Meal6 description",Money.valueOf(Currency.USD,50),
                MealType.Dessert,Ingredient.valueOf("Pancake,Honey,Cinnamon,Milk"),"https://www.readyseteat.com/sites/g/files/qyyrlu501/files/uploadedImages/img_2059_952.jpg");

        Meal meal10=Meal.build("Meal10","Meal10 description", Money.valueOf(Currency.USD,10),
                MealType.Sandwich,Ingredient.valueOf("Cheese,Lettuce,Cucumber,Turkey"),"https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15484.jpg?ext=.jpg");

        Meal meal2=Meal.build("Meal2","Meal2 description",Money.valueOf(Currency.USD,10),
                MealType.Breakfast,Ingredient.valueOf("Waffle,Apple,Honey"),"https://hips.hearstapps.com/del.h-cdn.co/assets/cm/15/10/54f63ec6bc6d3_-_gluten-free-banana-coconut-pancakes-recipe-fw0814-de.jpg");

        Meal meal7=Meal.build("Meal7","Meal7 description", Money.valueOf(Currency.USD,20),
                MealType.Salad,Ingredient.valueOf("Pepper,Onion,Lettuce,Chicken,Croutons,Corn,Cucumber,Tomato"),"https://img.allw.mn/food/thumbs/y8/yv/v4wvynv2_400x400.jpg");

        Meal meal8=Meal.build("Meal8","Meal8 description",Money.valueOf(Currency.USD,27),
                MealType.Burger,Ingredient.valueOf("Onion,Lettuce,Bacon,Potato,Pork"),"https://www.waitrose.com/content/dam/waitrose/recipes/images/b/E2E_WaitroseWeekendSunshineSpecial_36117_BestBurgers.gif/_jcr_content/renditions/cq5dam.thumbnail.400.400.png");

        Meal meal5=Meal.build("Meal5","Meal5 description", Money.valueOf(Currency.USD,50),
                MealType.MainMeal,Ingredient.valueOf("Lettuce,Chicken,Bacon,Rice,Pork"),"https://www.bbcgoodfoodme.com/assets/recipes/25261/original/tenderloin.png");

        if(mealRepository.findAll().isEmpty()){
            mealRepository.saveAll(Arrays.asList(meal1,meal2,meal3,meal4,meal5,meal6,meal7,meal8,meal9,meal10));
        }

    }
}
