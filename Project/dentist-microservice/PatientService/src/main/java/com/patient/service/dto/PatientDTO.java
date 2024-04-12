package com.patient.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patient.service.model.AppointmentRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private AddressDTO address;
    @JsonIgnore
    private List<AppointmentRequest> appointmentRequestList;
}
