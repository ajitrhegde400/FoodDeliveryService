package com.sample.fooddelivery.executive.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Incentives {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private Date date;

    @Column
    private double amount;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getIncentiveReceivedDate() {
        return date;
    }

    public void setIncentiveReceivedDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
