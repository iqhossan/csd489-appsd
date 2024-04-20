package com.mock.finalexam.service.impl;

import com.mock.finalexam.dto.EmployeeResponse;
import com.mock.finalexam.dto.ResponseDTO;
import com.mock.finalexam.dto.RetirementDTO;
import com.mock.finalexam.dto.RetirementResponse;
import com.mock.finalexam.model.Employee;
import com.mock.finalexam.repository.EmployeeRepository;
import com.mock.finalexam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployeeWithRetirementPlan(Employee employee) {
        return this.employeeRepository.save(employee);
    }


    @Override
    public List<ResponseDTO> getEmployeeList() {
        List<ResponseDTO> employees = this.employeeRepository.findAll(Sort.by("lastName")).stream()
                .map(e-> new ResponseDTO(
                            e.getEmployeeId(),
                            e.getFirstName(),
                            e.getLastName(),
                            e.getYearlySalary(),
                            (e.getRetirementPlan()!=null)?new RetirementDTO(
                                    e.getRetirementPlan().getPlanId(),
                                    e.getRetirementPlan().getReferenceNumber(),
                                    e.getRetirementPlan().getEnrollmentDate(),
                                    e.getRetirementPlan().getRetirementDate(),
                                    e.getRetirementPlan().getMonthlyContribution()
                            ):null
                    )).toList();
        return employees;

    }

    @Override
    public RetirementResponse getEmployeeById(Long employeeid) {
        Employee emp= this.employeeRepository.findById(employeeid).get();
        return new RetirementResponse(
                emp.getRetirementPlan().getPlanId(),
                emp.getRetirementPlan().getReferenceNumber(),
                emp.getRetirementPlan().getEnrollmentDate(),
                emp.getRetirementPlan().getRetirementDate(),
                emp.getRetirementPlan().getMonthlyContribution(),
                new EmployeeResponse(emp.getEmployeeId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getYearlySalary()
                )
        );
    }


}
