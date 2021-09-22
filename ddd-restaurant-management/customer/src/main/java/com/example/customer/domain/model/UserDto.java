package com.example.customer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private String username;

    private String name;

    private String surname;

    private String country;

    private String city;

    private String street;

    private int buildingNumber;

    private String telephoneNumber;

    public static UserDto valueOf(String username,String name,String surname, String country, String city,String street, int buildingNumber,String telephoneNumber){
        return new UserDto(username, name, surname,country,city, street, buildingNumber,telephoneNumber);
    }
}
