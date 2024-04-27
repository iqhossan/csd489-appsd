package com.appointment.service.repo;

import com.appointment.service.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByPatientId(Long patientId);
    List<Appointment> findAllByDentistId(Long dentistId);


}
