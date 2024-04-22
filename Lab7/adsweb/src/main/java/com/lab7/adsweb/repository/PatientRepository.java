package com.lab7.adsweb.repository;

import com.lab7.adsweb.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
