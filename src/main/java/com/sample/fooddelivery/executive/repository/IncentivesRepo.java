package com.sample.fooddelivery.executive.repository;

import com.sample.fooddelivery.executive.model.Incentives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IncentivesRepo extends JpaRepository<Incentives, Long> {

    public double findTotalIncentivesByDate(@Param(value="date") Date date);
}
