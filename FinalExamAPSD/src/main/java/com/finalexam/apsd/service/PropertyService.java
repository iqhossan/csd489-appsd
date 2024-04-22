package com.finalexam.apsd.service;

import com.finalexam.apsd.dto.PropertyDTO;
import com.finalexam.apsd.dto.PropertyResponse;
import com.finalexam.apsd.dto.RevenueResponse;
import com.finalexam.apsd.entity.Property;

import java.util.List;

public interface PropertyService {

    List<PropertyDTO> getPropertiesByState(String state);
    List<PropertyDTO> getAllProperties();

    RevenueResponse getTotalRevenue(String state);
}
