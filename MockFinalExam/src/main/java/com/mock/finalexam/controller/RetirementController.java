package com.mock.finalexam.controller;

import com.mock.finalexam.dto.EmployeeDTO;
import com.mock.finalexam.dto.EmployeeResponse;
import com.mock.finalexam.dto.ResponseDTO;
import com.mock.finalexam.dto.RetirementResponse;
import com.mock.finalexam.model.Employee;
import com.mock.finalexam.model.RetirementPlan;
import com.mock.finalexam.service.EmployeeService;
import com.mock.finalexam.service.RetirementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class RetirementController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RetirementService retirementService;


    @PostMapping("/")
    public ResponseEntity<RetirementResponse> addEmployeeRetirementPlan(@RequestBody EmployeeDTO employeeDTO){
        RetirementPlan plan = new RetirementPlan();
        plan.setReferenceNumber(employeeDTO.getReferenceNumber());
        plan.setRetirementDate(employeeDTO.getRetirementDate());
        plan.setEnrollmentDate(employeeDTO.getEnrollmentDate());
        plan.setMonthlyContribution(employeeDTO.getMonthlyContribution());

        Employee emp = new Employee();
        emp.setFirstName(employeeDTO.getFirstName());
        emp.setLastName(employeeDTO.getLastName());
        emp.setYearlySalary(employeeDTO.getYearlySalary());
       // emp.setRetirementPlan(plan);
        plan.setEmployee(emp);
        plan = this.retirementService.addEmployeeWithRetirementPlan(plan);

        RetirementResponse response = new RetirementResponse(plan.getPlanId(),plan.getReferenceNumber(),plan.getEnrollmentDate(),plan.getRetirementDate(),
                plan.getMonthlyContribution(),new EmployeeResponse(plan.getEmployee().getEmployeeId(),
                plan.getEmployee().getFirstName(), plan.getEmployee().getLastName(), plan.getEmployee().getYearlySalary()));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseDTO>> getAllEmployeeList(){
        List<ResponseDTO> emp = this.employeeService.getEmployeeList();
        return ResponseEntity.ok(emp);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<RetirementResponse> getPlanByEmployeeId(@PathVariable("employeeId") Long employeeId){
        RetirementResponse emp = this.employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(emp);
    }

    @GetMapping("/reports")
    public ResponseEntity< List<ResponseDTO>> getReports(){
        List<ResponseDTO> emp = this.retirementService.upcomingRetirements();
        return ResponseEntity.ok(emp);
    }

}
