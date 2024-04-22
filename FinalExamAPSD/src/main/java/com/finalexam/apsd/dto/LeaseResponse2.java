package com.finalexam.apsd.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LeaseResponse2 {
    private long leaseId;
    private String leaseReferenceNumber;
    private LocalDate startDate;
    private LocalDate endDate;

    @JsonIgnore
    private PropertyResponse property;

    public LeaseResponse2(long leaseId, String leaseReferenceNumber, LocalDate startDate, LocalDate endDate, PropertyResponse property) {
        this.leaseId = leaseId;
        this.leaseReferenceNumber = leaseReferenceNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.property = property;
    }

    public LeaseResponse2(long leaseId, String leaseReferenceNumber, LocalDate startDate, LocalDate endDate) {
        this.leaseId = leaseId;
        this.leaseReferenceNumber = leaseReferenceNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
