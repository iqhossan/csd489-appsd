package com.finalexam.apsd.service.Impl;

import com.finalexam.apsd.dto.LeaseResponse;
import com.finalexam.apsd.dto.LeaseInput;
import com.finalexam.apsd.dto.PropertyResponse;
import com.finalexam.apsd.entity.Lease;
import com.finalexam.apsd.entity.Property;
import com.finalexam.apsd.repository.LeaseRepository;
import com.finalexam.apsd.repository.PropertyRepository;
import com.finalexam.apsd.service.LeaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaseServiceImpl implements LeaseService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LeaseRepository repository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public List<LeaseResponse> getAllLease() {
        return this.repository.findAll(Sort.by("leaseReferenceNumber").descending()).stream()
                .map(data->new LeaseResponse(data.getLeaseId(),data.getLeaseReferenceNumber(),
                        data.getStartDate(),data.getEndDate(),
                        new PropertyResponse(data.getProperty().getPropertyId(),
                                data.getProperty().getPropertyRef(),
                                data.getProperty().getCity(),
                                data.getProperty().getState(),
                                data.getProperty().getMonthlyRentalRate()))).toList();
    }

    @Override
    public LeaseResponse addLease(int propertyId, LeaseInput leaseInput) {
        Property property = this.propertyRepository.findById(propertyId).get();
        if(property !=null ){
            leaseInput.setPropertyId(propertyId);
            Lease leaseData = modelMapper.map(leaseInput,Lease.class);
            Lease saveData = this.repository.save(leaseData);
            return new LeaseResponse(saveData.getLeaseId(),saveData.getLeaseReferenceNumber(),
                    saveData.getStartDate(),saveData.getEndDate(),
                    new PropertyResponse(property.getPropertyId(),
                            property.getPropertyRef(),
                            property.getCity(),
                            property.getState(),
                            property.getMonthlyRentalRate()));
            //modelMapper.map(saveData, LeaseDTO.class);
        }
        return null;
    }
}
