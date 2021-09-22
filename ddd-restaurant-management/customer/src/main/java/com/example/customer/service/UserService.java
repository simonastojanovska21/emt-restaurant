package com.example.customer.service;

import com.example.customer.domain.exceptions.InvalidUsernameException;
import com.example.customer.domain.exceptions.PasswordsDoNotMatchException;
import com.example.customer.domain.exceptions.UserNotFoundException;
import com.example.customer.domain.model.Customer;
import com.example.customer.domain.model.UserDto;
import com.example.customer.service.forms.RegisterForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<Customer> register(RegisterForm registerForm) throws PasswordsDoNotMatchException, InvalidUsernameException;
    Optional<UserDto> getCustomerInfo(String username) throws UserNotFoundException;
}
