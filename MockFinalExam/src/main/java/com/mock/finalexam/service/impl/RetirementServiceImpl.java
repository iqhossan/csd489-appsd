package com.mock.finalexam.service.impl;

import com.mock.finalexam.dto.ResponseDTO;
import com.mock.finalexam.dto.RetirementDTO;
import com.mock.finalexam.model.Employee;
import com.mock.finalexam.model.RetirementPlan;
import com.mock.finalexam.repository.EmployeeRepository;
import com.mock.finalexam.repository.RetirementRpository;
import com.mock.finalexam.service.RetirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class RetirementServiceImpl implements RetirementService {
    @Autowired
    public RetirementRpository retirementRpository;

    @Override
    public RetirementPlan addEmployeeWithRetirementPlan(RetirementPlan plan) {
        return this.retirementRpository.save(plan);
    }

    @Override
    public List<ResponseDTO> upcomingRetirements() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayNextMonth = today.plusMonths(1).withDayOfMonth(1);
        LocalDate lastDayNextMonth = firstDayNextMonth.with(TemporalAdjusters.lastDayOfMonth());
        List<RetirementPlan> responseDTOS = this.retirementRpository.findByRetirementDateBetweenOrderByRetirementDateAsc(firstDayNextMonth,lastDayNextMonth);


        return  responseDTOS.stream().map(e-> new ResponseDTO(
                e.getEmployee().getEmployeeId(),
                e.getEmployee().getFirstName(),
                e.getEmployee().getLastName(),
                e.getEmployee().getYearlySalary(),
                new RetirementDTO(e.getPlanId(), e.getReferenceNumber(),
                        e.getEnrollmentDate(),e.getRetirementDate(),e.getMonthlyContribution())
        )).toList();
    }


}
