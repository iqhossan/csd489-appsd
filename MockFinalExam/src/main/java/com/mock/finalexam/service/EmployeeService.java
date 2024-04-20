package com.mock.finalexam.service;


import com.mock.finalexam.dto.ResponseDTO;
import com.mock.finalexam.dto.RetirementResponse;
import com.mock.finalexam.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addEmployeeWithRetirementPlan(Employee employee);
    List<ResponseDTO> getEmployeeList();
    RetirementResponse getEmployeeById(Long employeeid);

}
