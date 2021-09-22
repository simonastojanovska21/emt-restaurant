package com.example.customer.config.data;

import com.example.customer.domain.model.Customer;
import com.example.customer.domain.model.Role;
import com.example.customer.domain.repository.UserRepository;
import com.example.customer.domain.valueobject.FullName;
import com.example.customer.domain.valueobject.PaymentInfo;
import com.example.sharedkernel.domain.valueObjects.Address;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData(){

        String encryptedCustomer=this.passwordEncoder.encode("P@ssword");
        FullName fullName=FullName.valueOf("User name","User surname");
        Address address=Address.valueOf("Makedonija","Skopje","Ulica 11",12);
        PaymentInfo paymentInfo=PaymentInfo.valueOf("User name",Long.valueOf("4512451245124512"),"10/21","012");

        Customer customer=Customer.build("user@test.com",encryptedCustomer,fullName,"070123465",paymentInfo,address, Role.ROLE_CUSTOMER);

        String encryptedAdmin=this.passwordEncoder.encode("P@ssword");
        FullName fullNameAdmin=FullName.valueOf("Admin name","Admin surname");
        Customer admin=Customer.build("admin@test.com",encryptedAdmin,fullNameAdmin,"070123456",paymentInfo,address,Role.ROLE_ADMIN);

        if(userRepository.findAll().isEmpty()){
            userRepository.saveAll(Arrays.asList(customer,admin));
        }
    }
}
