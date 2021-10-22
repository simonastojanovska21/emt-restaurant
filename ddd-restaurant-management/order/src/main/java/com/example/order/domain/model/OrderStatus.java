package com.example.order.domain.model;

//Edna naracka moze da ima nekolku statusi. Otkako korisnikot ke kreira naracka taa ima status ACTIVE, otkako
//se ja plati narackata PAID, koga ke se zavrshi do dostava, statusot na narackata e DELIVERED
//i vo slucaj narackata da se otkaza ima status CANCELED
public enum OrderStatus {
    ACTIVE,
    PAID,
    DELIVERED,
    CANCELED
}
