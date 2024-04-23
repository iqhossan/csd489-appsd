package com.finalexam.apsd.service.Impl;

import com.finalexam.apsd.dto.*;
import com.finalexam.apsd.entity.Lease;
import com.finalexam.apsd.entity.Property;
import com.finalexam.apsd.repository.PropertyRepository;
import com.finalexam.apsd.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertryServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PropertyDTO> getPropertiesByState(String state) {
        List<Property> property = this.repository.findPropertyByStateOrderByMonthlyRentalRateAsc(state);
        return property.stream().map(s->new PropertyDTO(s.getPropertyId(),s.getPropertyRef(),s.getCity(),
                s.getState(),s.getMonthlyRentalRate(),
                s.getLeaseList().stream().map(
                        d-> new LeaseResponse2(d.getLeaseId(),d.getLeaseReferenceNumber(),d.getStartDate(),d.getEndDate())
                ).toList()
        )).toList();
    }

    public List<PropertyDTO> getAllProperties() {
       List<Property> property =  this.repository.findAll();

        return property.stream().map(s->new PropertyDTO(s.getPropertyId(),s.getPropertyRef(),s.getCity(),
               s.getState(),s.getMonthlyRentalRate(),
                s.getLeaseList().stream().map(
                       d-> new LeaseResponse2(d.getLeaseId(),d.getLeaseReferenceNumber(),d.getStartDate(),d.getEndDate())
        ).toList()
        )).toList();
    }



    public RevenueResponse getTotalRevenue(String state) {
        List<Property> property = this.repository.findPropertyByStateOrderByMonthlyRentalRateAsc(state);

        int m1=0;
        double amt = 0;
//        for(Property p: property){
//            for(Lease l:p.getLeaseList()){
//                long monthsInYear2 = ChronoUnit.MONTHS.between(l.getStartDate(), l.getEndDate());
//                m1 += monthsInYear2;
//            }
//            amt += p.getMonthlyRentalRate() * m1;
//        }

       long totalmonths= property.stream().flatMapToLong(p->p.getLeaseList().stream()
               .mapToLong(l->ChronoUnit.MONTHS.between(l.getStartDate(), l.getEndDate()))).sum();
       double totalRent = property.stream().mapToDouble(p->p.getMonthlyRentalRate()).sum();

       amt = totalmonths * totalRent;
       return new RevenueResponse(state,amt);
    }
}
