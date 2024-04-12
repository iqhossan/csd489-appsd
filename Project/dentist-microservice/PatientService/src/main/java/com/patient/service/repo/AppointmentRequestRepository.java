package com.patient.service.repo;

import com.patient.service.model.AppointmentRequest;
import com.patient.service.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, Long> {

    public List<AppointmentRequest> findBypatient(Patient patient);
}
