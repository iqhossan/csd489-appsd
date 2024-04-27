package com.appointment.service.service.impl;


import com.appointment.service.model.Appointment;
import com.appointment.service.repo.AppointmentRepository;
import com.appointment.service.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return this.appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return this.appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllDoctorAppointment(Long dentistId) {
        return this.appointmentRepository.findAllByDentistId(dentistId);
    }

    @Override
    public List<Appointment> getAllPatientAppointment(Long patientId) {
        return this.appointmentRepository.findAllByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return this.appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long appointmentId) {
        return this.appointmentRepository.findById(appointmentId).get();
    }

    @Override
    public boolean deleteAppointment(Long appointmentId) {
        this.appointmentRepository.deleteById(appointmentId);
        return true;
    }
}
