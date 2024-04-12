package com.patient.service.service;


import com.patient.service.model.AppointmentRequest;
import com.patient.service.model.Patient;

import java.util.List;
import java.util.Optional;

public interface AppointmentRequestService {

    AppointmentRequest addAppointmentReq(AppointmentRequest appointmentRequest);
    AppointmentRequest updateAppointmentReq(AppointmentRequest appointmentRequest);
    List<AppointmentRequest> getAllAppointmentRequest();
    AppointmentRequest getAppointmentRequestById(Long requestId);
    void deleteAppointmentReq(Long requestId);

    List<AppointmentRequest> getAppointmentRequestsOfPatient(Patient patientId);
}
