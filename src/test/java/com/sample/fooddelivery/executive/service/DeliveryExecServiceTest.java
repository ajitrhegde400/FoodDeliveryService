package com.sample.fooddelivery.executive.service;

import com.sample.fooddelivery.executive.model.*;
import com.sample.fooddelivery.executive.repository.CustomerRepo;
import com.sample.fooddelivery.executive.repository.IncentivesRepo;
import com.sample.fooddelivery.executive.repository.OrderRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryExecServiceTest {


    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private IncentivesRepo incentivesRepo;

    @Mock
    private LocationService locationService;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    DeliveryExecService deliveryExecService;

    @Before
    public void setup(){
        deliveryExecService = new DeliveryExecService()
;        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void getCustomerContact_Test(){
        Customer customer = new Customer();
        customer.setPhoneNumber("9731558496");
        customer.setAddress("Street1, nagar, Bengaluru");
        when(customerRepo.findByName(Mockito.anyString())).thenReturn(customer);
        CustomerContact customerContact = deliveryExecService.getCustomerContact("Ajit");
        assertEquals("9731558496", customerContact.getPhoneNumber());
    }

    @Test
    public void getOrder_Test(){
        Orders order = new Orders();
        order.setDestinationAddress("Street1, nagar, Bengaluru");
        order.setPickupAddress("VijayNagar, Bengaluru");
        order.setOrderId(1123L);
        order.setQuantity(2);
        order.setDescription("Pulav");
        order.setStatus("Out for Delivery");
        when(orderRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(order));
        Orders orders = deliveryExecService.getOrder("1123");
        assertEquals("Out for Delivery", orders.getStatus());
    }

    @Test
    public void acceptOrder_Test() {
        Orders order = new Orders();
        order.setDestinationAddress("Street1, nagar, Bengaluru");
        order.setPickupAddress("VijayNagar, Bengaluru");
        order.setOrderId(1123L);
        order.setQuantity(2);
        order.setDescription("Pulav");
        order.setStatus("Accepted");
        when(orderRepo.save(Mockito.any())).thenReturn(order);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId("1123");
        orderStatus.setDescription("Pulav");
        orderStatus.setStatus("Accepted");
        Orders orders = deliveryExecService.acceptOrReject(orderStatus);
        assertEquals("Accepted", orders.getStatus());
    }

    @Test
    public void getLocation_Test(){
        Location location =   new Location("23.5678", "34.456");
        when(locationService.getLocation(Mockito.anyString())).thenReturn(location);
        Location pickUpLocation = deliveryExecService.getLocation("VijayNagar, Bengaluru");
        assertEquals("23.5678", pickUpLocation.getLatitude());
    }

    @Test
    public void makePayment_Success_Test(){
        Payment payment = new Payment();
        payment.setAmount(200.00);
        payment.setCustomerId(1223);
        payment.setCustomerName("Ajit");
        payment.setOrderId(1123);
        payment.setPaymentMethod("Credit Card");
        payment.setPaymentDetails("Food Order");
        when(paymentService.makePayment(Mockito.any())).thenReturn("SUCCESS");
        String result = deliveryExecService.makePayment(payment);
        assertEquals("Payment Successful", result);
    }

    @Test
    public void makePayment_Failed_Test(){
        Payment payment = new Payment();
        payment.setAmount(200.00);
        payment.setCustomerId(1223);
        payment.setCustomerName("Ajit");
        payment.setOrderId(1123);
        payment.setPaymentMethod("Credit Card");
        payment.setPaymentDetails("Food Order");
        when(paymentService.makePayment(Mockito.any())).thenReturn("FAILED");
        String result = deliveryExecService.makePayment(payment);
        assertEquals("Payment Failed", result);
    }

    @Test
    public void contactSupport_Test() throws Exception {
        assertEquals("180011111", deliveryExecService.contactSupport());
    }

    @Test
    public void updateOrder_Test() throws Exception {
        Orders order = new Orders();
        order.setDestinationAddress("Street1, nagar, Bengaluru");
        order.setPickupAddress("VijayNagar, Bengaluru");
        order.setOrderId(1123L);
        order.setQuantity(2);
        order.setDescription("Pulav");
        order.setStatus("Out for Delivery");
        when(orderRepo.save(Mockito.any())).thenReturn(order);
        when(orderRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(order));
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId("1123");
        orderStatus.setStatus("Out for Delivery");
        Orders updatedOrder = deliveryExecService.updateOrder(orderStatus);
        assertEquals("Out for Delivery", updatedOrder.getStatus());
    }


    @Test
    public void getTotalIncentives() throws Exception {
        Incentives incentives = new Incentives();
        incentives.setAmount(1230.00);
        incentives.setId(112233L);
        incentives.setIncentiveReceivedDate(new Date());
        when(incentivesRepo.findTotalIncentivesByDate(Mockito.any())).thenReturn(1230.00);
        double amount = deliveryExecService.getTotalIncentives("2012-12-20");
        assertEquals(String.valueOf(1230.00), String.valueOf(amount));
    }
}
