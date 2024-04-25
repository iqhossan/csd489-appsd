package com.patient.service.service;

import com.patient.service.helper.PatientNotFoundException;
import com.patient.service.model.Patient;

import java.util.List;

public interface PatientService {

    Patient addPatient(Patient patient);
    Patient updatePatient(Patient patient);
    List<Patient> getPatients(Integer pageNumber, Integer pageSize);
    Patient getPatient(Long patientId) throws Exception;
    void deletePatient(Long patientId);

}
