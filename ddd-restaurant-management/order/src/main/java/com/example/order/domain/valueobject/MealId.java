package com.example.order.domain.valueobject;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;

@Embeddable
public class MealId extends DomainObjectId {
    protected MealId() {
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
