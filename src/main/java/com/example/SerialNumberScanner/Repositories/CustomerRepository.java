package com.example.SerialNumberScanner.Repositories;

import com.example.SerialNumberScanner.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}