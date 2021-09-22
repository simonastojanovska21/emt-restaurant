package com.example.sharedkernel.domain.config;

public class TopicHolder {
    //ovoj topic ke se koristi koga korisnikot ke dodade novo jadenje vo narackata ili koga ke ja zgolemi kolicinata na
    //order item vo narackata
    public final static String TOPIC_MEAL_ADDED_IN_ORDER="meal-added-in-order";

    //ovoj topic ke se koristi koga korisnikot ke izbrishe nekoj order item, odnosno jadenje od narackata,
    //koga ke ja namali kolicinata na order item od narackata ili koga ke se otkaze nekoja naracka
    public final static String TOPIC_MEAL_REMOVED_FROM_ORDER="meal-removed-from-order";

    //ovoj topic se koristi koga korisnikot ke plati za svojata naracka. Sleden cekor e kreiranje na delivery objekt
    public final static String TOPIC_SUCCESSFULLY_PAID_FOR_ORDER="successfully-paid-for-order";

    //ovoj topic se koristi koga ke se zavrshi so dostava. Otkako narackata ke bide dostavena, se menuva nejziniot status na DELIVERED
    public final static String TOPIC_ORDER_SUCCESSFULLY_DELIVERED="successfully-delivered-order";
}
