package com.appointment.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private int patientId;
    private int dentistId;
    private int requestId;
    private int branchId;
    private DentistDTO dentistDTO;
    private PatientDTO patientDTO;
    private BranchDTO branchDTO;
    private AppointmentRequestDTO appointmentRequestDTO;
}
