package com.mock.finalexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private long employeeId;
    private String firstName;
    private String lastName;
    private double yearlySalary;
    private RetirementDTO retirementDTO;
}
