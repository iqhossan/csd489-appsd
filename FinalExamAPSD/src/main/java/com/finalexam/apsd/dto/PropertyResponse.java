package com.finalexam.apsd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class PropertyResponse {
    private int propertyId;
    private String propertyRef;
    private String city;
    private String state;
    private double monthlyRentalRate;

    public PropertyResponse(int propertyId, String propertyRef, String city, String state, double monthlyRentalRate) {
        this.propertyId = propertyId;
        this.propertyRef = propertyRef;
        this.city = city;
        this.state = state;
        this.monthlyRentalRate = monthlyRentalRate;
    }
}
