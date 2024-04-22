package com.lab7.adsweb.service;

import com.lab7.adsweb.dto.PatientInput;
import com.lab7.adsweb.dto.PatientResponse;
import com.lab7.adsweb.model.Patient;

import java.util.List;

public interface PatientService {

    List<PatientResponse> GetAllPatient();
    List<PatientResponse> getPatientById(Long patientId);
    PatientResponse registerPatient(PatientInput patientInput);
    Patient updatePatient(Patient patient);
    void deletePatient(Long patientId);
    List<PatientInput> serachPatient(String queryString);
}
