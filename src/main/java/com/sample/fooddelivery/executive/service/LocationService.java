package com.sample.fooddelivery.executive.service;

import com.sample.fooddelivery.executive.model.Location;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    public Location getLocation(String address){
        //Instead call Google API's Location Service
        return new Location("23.5678", "34.456");
    }
}
