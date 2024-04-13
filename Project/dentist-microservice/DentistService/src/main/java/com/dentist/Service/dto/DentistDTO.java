package com.dentist.Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DentistDTO {
    private Long dentistId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String specialization;
    private int maxAppointment=0;
    private List<AppointmentDTO> AppointmentDTO;
}
