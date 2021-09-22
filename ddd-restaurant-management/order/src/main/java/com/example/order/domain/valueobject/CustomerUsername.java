package com.example.order.domain.valueobject;

import com.example.sharedkernel.domain.base.DomainObjectId;
import com.sun.istack.NotNull;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerUsername{
    private final String username;

    protected CustomerUsername(){
        this.username="";
    }

    public CustomerUsername(@NotNull String username){
        this.username=username;
    }

    public static CustomerUsername valueOf(String username){
        return new CustomerUsername(username);
    }
}
