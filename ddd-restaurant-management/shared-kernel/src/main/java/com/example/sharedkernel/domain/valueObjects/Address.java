package com.example.sharedkernel.domain.valueObjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Embeddable;

//Vrednosen objekt koj ja definira adresata na koja zivee korisnikot i adresata na koja treba da se dostavi narackata
//Bidejki se koristi vo 2 ograniceni konteksti ovoj vrednosen objekt e smesten vo shared-kernel
@Embeddable
@Getter
public class Address implements ValueObject {

    private final String country;

    private final String city;

    private final String street;

    private final int buildingNumber;

    protected Address()
    {
        this.country=null;
        this.city=null;
        this.street=null;
        this.buildingNumber=-1;
    }

    public Address(@NotNull String country,@NotNull String city, @NotNull String street, @NotNull int buildingNumber)
    {
        this.country=country;
        this.city=city;
        this.street=street;
        this.buildingNumber=buildingNumber;
    }

    public static Address valueOf(String country,String city, String street, int buildingNumber)
    {
        return new Address(country,city,street,buildingNumber);
    }
}
