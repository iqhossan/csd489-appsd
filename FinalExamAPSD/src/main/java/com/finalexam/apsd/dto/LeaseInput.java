package com.finalexam.apsd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaseInput {
    private long leaseId;
    private String leaseReferenceNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private int propertyId;
}
