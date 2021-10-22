package com.example.customer.service.forms;


import com.example.customer.domain.model.Customer;
import com.example.customer.domain.model.Role;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

//Data transfer object koj se koristi za JWT avtentikacija
@Data
public class UserDetailsDto {
    private String username;
    private Role role;

    public static UserDetailsDto of(Customer user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role = user.getUserRole();
        return details;
    }
}
