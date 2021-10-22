package com.example.customer.domain.model;

import org.springframework.security.core.GrantedAuthority;

//Enumeracija koja ja ozanacuva ulogata na korsinikot
//Se koristi spring security pa ovaa enumeracija go implementira interfejsot GrantedAuthority
public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }
}
