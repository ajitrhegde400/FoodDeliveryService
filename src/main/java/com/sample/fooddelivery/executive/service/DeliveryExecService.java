package com.sample.fooddelivery.executive.service;

import com.sample.fooddelivery.executive.model.*;
import com.sample.fooddelivery.executive.repository.CustomerRepo;
import com.sample.fooddelivery.executive.repository.IncentivesRepo;
import com.sample.fooddelivery.executive.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@Service
public class DeliveryExecService {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    IncentivesRepo incentivesRepo;

    @Autowired
    LocationService locationService;

    @Autowired
    PaymentService paymentService;

    public CustomerContact getCustomerContact(String customerName){
        Customer customer = customerRepo.findByName(customerName);
        CustomerContact customerContact = new CustomerContact();
        customerContact.setPhoneNumber("9731558496");
        customerContact.setAddress("Street1, nagar, Bengaluru");
        return customerContact;
    }

    public Orders getOrder(String orderId) {
        Long id = Long.parseLong(orderId);
        Optional<Orders> order = orderRepo.findById(id);
        return order.get();
    }

    public Orders acceptOrReject(OrderStatus orderStatus) {
        Orders orderObj = new Orders();
        orderObj.setStatus(orderStatus.getStatus());
        return orderRepo.save(orderObj);
    }

    public Location getLocation(String address) {
        //Call Google API location service and populate the Location Object
        return locationService.getLocation(address);
    }

    public String makePayment(Payment payment){
        //Call Payment Service
       String result =  paymentService.makePayment(payment);
       if ("SUCCESS".equalsIgnoreCase(result))
           return "Payment Successful";
       else
           return "Payment Failed";

    }

    public double getTotalIncentives(String date) throws ParseException {
        Date formattedDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
        double incentives = incentivesRepo.findTotalIncentivesByDate(formattedDate);
        return 1230.00;

    }

    public String contactSupport(){

        return "180011111";
    }

    public Orders updateOrder(OrderStatus status) {
        Long id = Long.parseLong(status.getOrderId());
        Optional<Orders> order = orderRepo.findById(id);
        Orders orderObj = order.get();
        orderObj.setStatus(status.getStatus());
        orderObj.setDescription(status.getDescription());
        return orderRepo.save(orderObj);
    }
}
