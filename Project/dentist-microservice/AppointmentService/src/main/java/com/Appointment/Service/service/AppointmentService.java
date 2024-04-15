package com.appointment.service.service;


import com.appointment.service.model.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointment(Appointment appointment);
    List<Appointment> getAllAppointment();
    Appointment getAppointmentById(Long appointmentId);
    boolean deleteAppointment(Long appointmentId);
}
