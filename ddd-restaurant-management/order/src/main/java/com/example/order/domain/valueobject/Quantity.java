package com.example.order.domain.valueobject;

import com.example.sharedkernel.domain.base.ValueObject;
import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Embeddable;

//Quantity e vrednosen objekt koj ja oznacuva kolicinata na order items vo narackata. Ima metodi za zgolemuvanje i namaluvanje
//na kolicinata na order item vo narackata.
@Embeddable
@Getter
public class Quantity implements ValueObject {

    private final int quantity;

    protected Quantity(){
        this.quantity=1;
    }

    public Quantity(@NotNull int quantity){
        this.quantity=quantity;
    }

    public Quantity valueOf(int quantity){
        return new Quantity(quantity);
    }


    public Quantity addQuantity(){
        return new Quantity(quantity+1);
    }

    public Quantity removeQuantity(){
        return new Quantity(quantity-1);
    }
}
