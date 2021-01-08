package com.sample.fooddelivery.executive.controller;

import com.sample.fooddelivery.executive.model.*;
import com.sample.fooddelivery.executive.service.DeliveryExecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/deliveryExec")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class DeliveryExecutiveController {

    @Autowired
    DeliveryExecService deliveryExecService;

    @GetMapping(value = "/getCustomerContact")
    @ResponseBody
    public CustomerContact getCustomerContact(@RequestParam  String customerName){
        return deliveryExecService.getCustomerContact(customerName);
    }

    @GetMapping(value = "/getOrder")
    @ResponseBody
    public Orders getOrder(@RequestParam  String orderId){
        return deliveryExecService.getOrder(orderId);
    }

    @PostMapping(value = "/order")
    @ResponseBody
    public Orders acceptOrder(@RequestBody OrderStatus status){
        return deliveryExecService.acceptOrReject(status);
    }

    @GetMapping(value = "/getLocation")
    @ResponseBody
    public Location getLocation(@RequestParam  String address){
        return deliveryExecService.getLocation(address);
    }

    @PostMapping(value = "/payment")
    @ResponseBody
    public String makePayment( @RequestBody Payment payment){
        return deliveryExecService.makePayment(payment);
    }

    @GetMapping(value = "/contactSupport")
    @ResponseBody
    public String getSupportContact(){
        return deliveryExecService.contactSupport();
    }

    @PutMapping(value = "/updateOrder")
    @ResponseBody
    public Orders updateOrder(@RequestBody OrderStatus status){
        return deliveryExecService.updateOrder(status);
    }

    @GetMapping(value = "/totalIncentives")
    @ResponseBody
    public double getTotalIncentives(@RequestParam String date) throws ParseException {
        return deliveryExecService.getTotalIncentives(date);
    }



}
