package com.example.order.domain.model;

import com.example.order.domain.valueobject.MealId;
import com.example.order.domain.valueobject.Quantity;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.valueObjects.Money;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {

    private Money orderItemPrice;

    private Quantity orderItemQuantity;

    @AttributeOverride(name = "id", column = @Column(name = "meal_id", nullable = false))
    private MealId mealId;

    protected OrderItem() {
        super(DomainObjectId.randomId(OrderItemId.class));
    }

    public OrderItem(@NonNull MealId mealId, @NonNull Money orderItemPrice, Quantity orderItemQuantity) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.mealId = mealId;
        this.orderItemPrice = orderItemPrice;
        this.orderItemQuantity = orderItemQuantity;
    }

    public static OrderItem build(MealId mealId,Money orderItemPrice,Quantity quantity){
        OrderItem orderItem=new OrderItem();
        orderItem.mealId=mealId;
        orderItem.orderItemPrice=orderItemPrice;
        orderItem.orderItemQuantity=quantity;
        return orderItem;
    }

    public Money subtotal() {
        return orderItemPrice.multiply(orderItemQuantity.getQuantity());
    }

    public void addQuantity(){
        this.orderItemQuantity= orderItemQuantity.addQuantity();
    }

    public void removeQuantity(){
        this.orderItemQuantity= orderItemQuantity.removeQuantity();
    }
}
