package com.mock.finalexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetirementResponse {

    private long planId;
    private String referenceNumber;
    private LocalDate enrollmentDate;
    private LocalDate retirementDate;
    private double monthlyContribution;
    private EmployeeResponse employeeResponse;

}
