package com.lab7.adsweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {
    private Long patientId;
    private String firstName;
    private String lastName;
    private AddressResponse response;
}
