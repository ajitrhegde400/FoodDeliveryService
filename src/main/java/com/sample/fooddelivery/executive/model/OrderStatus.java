package com.sample.fooddelivery.executive.model;

import java.io.Serializable;

public class OrderStatus implements Serializable {

    private String OrderId;

    private String status;

    private String description;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
