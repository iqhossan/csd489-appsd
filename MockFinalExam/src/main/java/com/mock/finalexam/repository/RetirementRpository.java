package com.mock.finalexam.repository;

import com.mock.finalexam.dto.ResponseDTO;
import com.mock.finalexam.model.RetirementPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RetirementRpository extends JpaRepository<RetirementPlan, Long> {


    List<RetirementPlan> findByRetirementDateBetweenOrderByRetirementDateAsc(LocalDate start, LocalDate end);
}
