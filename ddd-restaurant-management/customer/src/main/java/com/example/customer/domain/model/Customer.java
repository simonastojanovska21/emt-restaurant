package com.example.customer.domain.model;

import com.example.customer.domain.valueobject.FullName;
import com.example.sharedkernel.domain.valueObjects.Address;
import com.example.customer.domain.valueobject.PaymentInfo;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "customer")
@Getter
public class Customer implements UserDetails {

    @Id
    private String username;

    private String password;

    private FullName fullName;

    private String telephoneNumber;

    private PaymentInfo paymentInfo;

    private Address address;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    public Customer(){

    }

    public static Customer build (String username, String password,FullName fullName,String telephoneNumber, PaymentInfo paymentInfo,Address address,Role userRole){
        Customer customer=new Customer();
        customer.username=username;
        customer.password=password;
        customer.fullName=fullName;
        customer.telephoneNumber=telephoneNumber;
        customer.paymentInfo=paymentInfo;
        customer.address=address;
        customer.userRole=userRole;
        return customer;
    }

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(userRole);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Role getUserRole(){
        return this.userRole;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
