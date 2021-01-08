package com.sample.fooddelivery.executive.repository;

import com.sample.fooddelivery.executive.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {

}
