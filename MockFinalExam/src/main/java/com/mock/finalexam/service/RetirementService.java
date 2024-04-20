package com.mock.finalexam.service;

import com.mock.finalexam.dto.ResponseDTO;
import com.mock.finalexam.model.Employee;
import com.mock.finalexam.model.RetirementPlan;

import java.util.List;

public interface RetirementService {

    RetirementPlan addEmployeeWithRetirementPlan(RetirementPlan plan);
    List<ResponseDTO> upcomingRetirements();
}
