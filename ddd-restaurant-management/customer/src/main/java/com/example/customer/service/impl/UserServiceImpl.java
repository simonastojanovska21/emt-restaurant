package com.example.customer.service.impl;

import com.example.customer.domain.exceptions.InvalidUsernameException;
import com.example.customer.domain.exceptions.PasswordsDoNotMatchException;
import com.example.customer.domain.exceptions.UserNotFoundException;
import com.example.customer.domain.model.Customer;
import com.example.customer.domain.model.Role;
import com.example.customer.domain.model.UserDto;
import com.example.customer.domain.repository.UserRepository;
import com.example.customer.domain.valueobject.FullName;
import com.example.customer.domain.valueobject.PaymentInfo;
import com.example.customer.service.UserService;
import com.example.customer.service.forms.RegisterForm;
import com.example.sharedkernel.domain.valueObjects.Address;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    @Override
    public Optional<Customer> register(RegisterForm registerForm) throws PasswordsDoNotMatchException, InvalidUsernameException {
        Objects.requireNonNull(registerForm,"register form must not be null");

        if(!registerForm.getPassword().equals(registerForm.getRepeatedPassword()))
            throw new PasswordsDoNotMatchException();

        if(this.userRepository.findByUsername(registerForm.getUsername()).isPresent())
            throw new InvalidUsernameException();

        Customer customer=toDomainObject(registerForm);
        this.userRepository.saveAndFlush(customer);
        return Optional.of(customer);
    }

    @Override
    public Optional<UserDto> getCustomerInfo(String username) throws UserNotFoundException {
        Customer customer=this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return Optional.of(UserDto.valueOf(customer.getUsername(),customer.getFullName().getName(),customer.getFullName().getSurname(),
                customer.getAddress().getCountry(),customer.getAddress().getCity(),
                customer.getAddress().getStreet(),customer.getAddress().getBuildingNumber(),customer.getTelephoneNumber()));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }

    private Customer toDomainObject(RegisterForm registerForm){
        String encrypted=this.passwordEncoder.encode(registerForm.getPassword());
        FullName fullName=new FullName(registerForm.getName(),registerForm.getSurname());
        PaymentInfo paymentInfo=new PaymentInfo(registerForm.getNameOfCardHolder(),registerForm.getCreditCardNumber(),registerForm.getValidThrough(),registerForm.getCCV());
        Address address=new Address(registerForm.getCountry(),registerForm.getCity(),registerForm.getStreet(),registerForm.getBuildingNumber());
        return Customer.build(registerForm.getUsername(),encrypted,fullName,registerForm.getTelephoneNumber(),
                paymentInfo,address, Role.ROLE_CUSTOMER);
    }
}
