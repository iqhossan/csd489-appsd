package com.finalexam.apsd.dto;

import com.finalexam.apsd.entity.Lease;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PropertyDTO {
    private int propertyId;
    private String propertyRef;
    private String city;
    private String state;
    private double monthlyRentalRate;
    private List<LeaseResponse2> leaseList;

    public PropertyDTO(int propertyId, String propertyRef, String city, String state, double monthlyRentalRate) {
        this.propertyId = propertyId;
        this.propertyRef = propertyRef;
        this.city = city;
        this.state = state;
        this.monthlyRentalRate = monthlyRentalRate;
    }

    public PropertyDTO(int propertyId, String propertyRef, String city, String state, double monthlyRentalRate, List<LeaseResponse2> leaseList) {
        this.propertyId = propertyId;
        this.propertyRef = propertyRef;
        this.city = city;
        this.state = state;
        this.monthlyRentalRate = monthlyRentalRate;
        this.leaseList = leaseList;
    }
}
