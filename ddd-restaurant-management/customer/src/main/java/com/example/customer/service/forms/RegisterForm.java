package com.example.customer.service.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;

//Pri registracija korisnikot gi vnesuva informaciite koi se baraat od nego.
//Site tie se grupirano vo klasata RegisterForm i od frontend aplikacijata se isprakjaat na backend aplikacijata preku
// soodvetniot metod

@Data
public class RegisterForm {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String repeatedPassword;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String telephoneNumber;

    @NotNull
    private String nameOfCardHolder;

    @NotNull
    private Long creditCardNumber;

    @NotNull
    private String validThrough;

    @NotNull
    private String CCV;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private int buildingNumber;
}
