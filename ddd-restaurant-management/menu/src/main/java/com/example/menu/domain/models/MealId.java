package com.example.menu.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class MealId extends DomainObjectId {
    private MealId() {
        super(MealId.randomId(MealId.class).getId());
    }

    public MealId(@NonNull String uuid) {
        super(uuid);
    }

    public static MealId of(String uuid){
        MealId mealId=new MealId(uuid);
        return mealId;
    }
}
