package com.example.customer.domain.repository;

import com.example.customer.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Customer,String> {
    Optional<Customer> findByUsername(String username);
}
