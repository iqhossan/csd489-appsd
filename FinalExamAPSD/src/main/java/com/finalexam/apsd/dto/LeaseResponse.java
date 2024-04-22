package com.finalexam.apsd.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
public class LeaseResponse {
    private long leaseId;
    private String leaseReferenceNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private PropertyResponse property;

    public LeaseResponse(long leaseId, String leaseReferenceNumber, LocalDate startDate, LocalDate endDate, PropertyResponse property) {
        this.leaseId = leaseId;
        this.leaseReferenceNumber = leaseReferenceNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.property = property;
    }

    public LeaseResponse(long leaseId, String leaseReferenceNumber, LocalDate startDate, LocalDate endDate) {
        this.leaseId = leaseId;
        this.leaseReferenceNumber = leaseReferenceNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
