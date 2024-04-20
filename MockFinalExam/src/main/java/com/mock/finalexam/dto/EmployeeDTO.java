package com.mock.finalexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private long employeeId;
    private String firstName;
    private String lastName;
    private double yearlySalary;
    private String referenceNumber;
    private LocalDate enrollmentDate;
    private LocalDate retirementDate;
    private double monthlyContribution;

}
