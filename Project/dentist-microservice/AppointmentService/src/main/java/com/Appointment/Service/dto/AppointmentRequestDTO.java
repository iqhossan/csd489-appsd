package com.appointment.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDTO {
    private Long AppointRequestId;
    private LocalDate requestDate;
    private LocalTime requestTime;
    private LocalDate changesDate;
    private LocalTime changesTime;
}
