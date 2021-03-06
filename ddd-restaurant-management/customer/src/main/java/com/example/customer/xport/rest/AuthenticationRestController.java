package com.example.customer.xport.rest;

import com.example.customer.domain.model.UserDto;
import com.example.customer.service.UserService;
import com.example.customer.service.forms.RegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Rest controller so koj frontend aplikacijata komunucira so customer backend applikacijata
//Ima 2 metodi, so edniot se ovozmozuva registracija na korisnici, a so drugiot se zemaat informacii za korisnik
//So najavata na korisnici se spravuva spring security

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authentication")
@AllArgsConstructor
public class AuthenticationRestController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterForm registerForm){
        return this.userService.register(registerForm)
                .map(user->ResponseEntity.ok().body(user))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @GetMapping("/customerDetails/{username}")
    public ResponseEntity<UserDto> getInfoAboutCustomer(@PathVariable String username){
        return this.userService.getCustomerInfo(username)
                .map(user->ResponseEntity.ok().body(user))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
}
