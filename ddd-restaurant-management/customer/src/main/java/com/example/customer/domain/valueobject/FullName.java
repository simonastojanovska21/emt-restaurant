package com.example.customer.domain.valueobject;

import com.example.sharedkernel.domain.base.ValueObject;
import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Embeddable;

//Vrednosen objekt so koj se cuvaat ime i prezime na korisnik, za skrateno poshuvame FullName
@Embeddable
@Getter
public class FullName implements ValueObject {

    private final String name;

    private final String surname;

    protected FullName()
    {
        this.name=null;
        this.surname=null;
    }

    private FullName(@NotNull String name, @NotNull String surname)
    {
        this.name=name;
        this.surname=surname;
    }

    public static FullName valueOf(String name, String surname)
    {
        return new FullName(name,surname);
    }
}
