package com.example.customer.service;

import com.example.customer.domain.exceptions.UsernameAlreadyExistsException;
import com.example.customer.domain.exceptions.PasswordsDoNotMatchException;
import com.example.customer.domain.exceptions.UserNotFoundException;
import com.example.customer.domain.model.Customer;
import com.example.customer.domain.model.UserDto;
import com.example.customer.service.forms.RegisterForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    /*
    Metod za registritanje na korisnici koj frla isklucok dokolku vrednosta na password i repeatedPassword ne se sofpagja (PasswordsDoNotMatchException)
    i frla isklucok dokolku vekje postoi korisnik so toa username vo bazata (InvalidUsernameException)
     */
    Optional<Customer> register(RegisterForm registerForm) throws PasswordsDoNotMatchException, UsernameAlreadyExistsException;

    /*
    Metod koj se koristi za prevzemanje na informacii za edne korisnik. Se prevzemaata samo informacii od interes
    pa ovoj metod vrakja UserDto
     */
    Optional<UserDto> getCustomerInfo(String username) throws UserNotFoundException;
}
