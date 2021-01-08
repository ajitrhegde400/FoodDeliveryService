package com.sample.fooddelivery.executive.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sample.fooddelivery.executive.model.*;
import com.sample.fooddelivery.executive.service.DeliveryExecService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DeliveryExecutiveController.class)
@AutoConfigureMockMvc
public class DeliveryExecutiveControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private DeliveryExecService deliveryExecService;


    @InjectMocks
    DeliveryExecutiveController deliveryExecutiveController;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getCustomerContact_Test() throws Exception {
       CustomerContact customerContact = new CustomerContact();
        customerContact.setPhoneNumber("9731558496");
        customerContact.setAddress("Street1, nagar, Bengaluru");
        when(deliveryExecService.getCustomerContact(Mockito.anyString())).thenReturn(customerContact);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/deliveryExec/getCustomerContact").param("customerName","Ajit")
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(("{'phoneNumber':'9731558496','address':'Street1, nagar, Bengaluru'}")))
                .andReturn();
    }


    @Test
    public void getOrder_Test() throws Exception {
        Orders order = new Orders();
        order.setDestinationAddress("Street1, nagar, Bengaluru");
        order.setPickupAddress("VijayNagar, Bengaluru");
        order.setOrderId(1123L);
        order.setQuantity(2);
        order.setDescription("Pulav");
        order.setStatus("Out for Delivery");
        when(deliveryExecService.getOrder(Mockito.anyString())).thenReturn(order);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/deliveryExec/getOrder").param("orderId","1123")
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(("{'orderId':1123,'status':'Out for Delivery','description':'Pulav','quantity':2,'pickupAddress':'VijayNagar, Bengaluru','destinationAddress':'Street1, nagar, Bengaluru'}")))
                .andReturn();
    }


    @Test
    public void acceptOrder_Test() throws Exception {
        Orders order = new Orders();
        order.setDestinationAddress("Street1, nagar, Bengaluru");
        order.setPickupAddress("VijayNagar, Bengaluru");
        order.setOrderId(1123L);
        order.setQuantity(2);
        order.setDescription("Pulav");
        order.setStatus("Accepted");
        when(deliveryExecService.acceptOrReject(Mockito.any())).thenReturn(order);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/deliveryExec/order").content(mapToJson(order))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
    }

    @Test
    public void getLocation_Test() throws Exception {
        Location location =   new Location("23.5678", "34.456");
        when(deliveryExecService.getLocation(Mockito.anyString())).thenReturn(location);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/deliveryExec/getLocation").param("address","Street1, nagar, Bengaluru")
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(("{'latitude':'23.5678','longitude':'34.456'}")))
                .andReturn();
    }

   @Test
    public void makePayment_Test() throws Exception {
        Payment payment = new Payment();
        payment.setAmount(200.00);
        payment.setCustomerId(1223);
        payment.setCustomerName("Ajit");
        payment.setOrderId(1123);
        payment.setPaymentMethod("Credit Card");
        payment.setPaymentDetails("Food Order");
        when(deliveryExecService.makePayment(Mockito.any())).thenReturn("Payment Successful");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/deliveryExec/payment").content(mapToJson(payment))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void contactSupport_Test() throws Exception {
        when(deliveryExecService.contactSupport()).thenReturn("180011111");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/deliveryExec/contactSupport")
        ).andExpect(status().isOk())
                .andReturn();
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
        when(deliveryExecService.updateOrder(Mockito.any())).thenReturn(order);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/deliveryExec/updateOrder").content(mapToJson(order))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getTotalIncentives() throws Exception {
        Incentives incentives = new Incentives();
        incentives.setAmount(1200.00);
        incentives.setId(112233L);
        incentives.setIncentiveReceivedDate(new Date());
        when(deliveryExecService.getTotalIncentives(Mockito.anyString())).thenReturn(1200.00);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/deliveryExec/totalIncentives").param("date", "2020-12-20")
        ).andExpect(status().isOk())
                .andReturn();
    }


    //This method converts the input object to json format through object Mapper
    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(obj );
        return requestJson;
    }
}


