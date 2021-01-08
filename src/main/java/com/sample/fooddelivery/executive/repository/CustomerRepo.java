package com.sample.fooddelivery.executive.repository;

import com.sample.fooddelivery.executive.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByName(String customerName);
}
