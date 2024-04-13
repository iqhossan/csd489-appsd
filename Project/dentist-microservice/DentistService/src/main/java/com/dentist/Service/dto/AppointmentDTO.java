package com.dentist.Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Long patientId;
    PatientDTO patientDTO;
}
