package com.sample.fooddelivery.executive.service;

import com.sample.fooddelivery.executive.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String makePayment(Payment payment) {
        //Call to payment gateway service to complete the payment
        return "SUCCESS";
    }
}
