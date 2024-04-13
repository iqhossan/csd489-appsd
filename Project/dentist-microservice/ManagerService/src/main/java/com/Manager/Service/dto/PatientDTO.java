package com.address.Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long patientId;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private double dues = 0.0;
}
