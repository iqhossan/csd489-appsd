package com.lab7.adsweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientInput {

    private Long patientId;
    private String firstName;
    private String lastName;
    private String street;
    private String state;
    private String city;
    private int zipcode;
}
