package com.example.customer.xport.rest;

import com.example.customer.domain.model.Customer;
import com.example.customer.domain.model.UserDto;
import com.example.customer.service.UserService;
import com.example.customer.service.forms.RegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
