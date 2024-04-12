package com.patient.service.service.impl;

import com.netflix.discovery.converters.Auto;
import com.patient.service.model.AppointmentRequest;
import com.patient.service.model.Patient;
import com.patient.service.repo.AppointmentRequestRepository;
import com.patient.service.repo.PatientRepository;
import com.patient.service.service.AppointmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentRequestServiceImpl implements AppointmentRequestService {

    @Autowired
    private AppointmentRequestRepository appointmentRequestRepository;

    @Autowired
    private PatientRepository patientRepository;
    @Override
    public AppointmentRequest addAppointmentReq(AppointmentRequest appointmentRequest) {

        Long patientId = appointmentRequest.getPatient().getPatientId();
        if (patientId != null) {
            // Fetch patient data from the database
            Patient patient = patientRepository.findById(patientId).orElse(null);
            if (patient != null) {
                // Set the fetched patient object in the appointment request
                appointmentRequest.setPatient(patient);
                // Save the appointment request
                return appointmentRequestRepository.save(appointmentRequest);
            }
        }
        return null;
    }

    @Override
    public AppointmentRequest updateAppointmentReq(AppointmentRequest appointmentRequest) {
        return this.appointmentRequestRepository.save(appointmentRequest);
    }

    @Override
    public List<AppointmentRequest> getAllAppointmentRequest() {
        return this.appointmentRequestRepository.findAll();
    }

    @Override
    public AppointmentRequest getAppointmentRequestById(Long requestId) {
        return this.appointmentRequestRepository.findById(requestId).get();
    }

    @Override
    public void deleteAppointmentReq(Long requestId) {
        this.appointmentRequestRepository.deleteById(requestId);
    }

    @Override
    public List<AppointmentRequest> getAppointmentRequestsOfPatient(Patient patientId) {
        List<AppointmentRequest> requests = this.appointmentRequestRepository.findBypatient(patientId);
        return requests;
    }
}
