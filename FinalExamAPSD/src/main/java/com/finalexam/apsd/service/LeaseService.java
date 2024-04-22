package com.finalexam.apsd.service;

import com.finalexam.apsd.dto.LeaseResponse;
import com.finalexam.apsd.dto.LeaseInput;

import java.util.List;

public interface LeaseService {

    List<LeaseResponse> getAllLease();
    LeaseResponse addLease(int propertyId, LeaseInput leaseInput);
}
