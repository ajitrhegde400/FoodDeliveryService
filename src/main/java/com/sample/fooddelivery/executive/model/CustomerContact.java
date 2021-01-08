package com.sample.fooddelivery.executive.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class CustomerContact implements Serializable {

    private String phoneNumber;
    private String address;

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
