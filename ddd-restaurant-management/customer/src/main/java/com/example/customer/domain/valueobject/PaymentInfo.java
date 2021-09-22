package com.example.customer.domain.valueobject;

import com.example.sharedkernel.domain.base.ValueObject;
import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Embeddable;
//Vrednosen objekt vo koj se cuvaat informacii za plakjanje
//Vo idnina ovoj vrednosen objekt moze da se definira kako entitet, taka shto za sekoj korisnik moze da imame
//edna ili povekje informacii za plakjanje
@Embeddable
@Getter
public class PaymentInfo implements ValueObject {

    private final String nameOfCardHolder;

    private final Long creditCardNumber;

    private final String validThrough;

    private final String CCV;

    protected PaymentInfo()
    {
        this.nameOfCardHolder=null;
        this.creditCardNumber=null;
        this.validThrough=null;
        this.CCV=null;
    }

    public PaymentInfo(@NotNull String nameOfCardHolder,@NotNull Long creditCardNumber, @NotNull String validThrough, @NotNull String CCV)
    {
        this.nameOfCardHolder=nameOfCardHolder;
        this.creditCardNumber=creditCardNumber;
        this.validThrough=validThrough;
        this.CCV=CCV;
    }

    public static PaymentInfo valueOf(String nameOfCardHolder,Long creditCardNumber,String validThrough, String CCV)
    {
        return new PaymentInfo(nameOfCardHolder,creditCardNumber,validThrough,CCV);
    }

}
