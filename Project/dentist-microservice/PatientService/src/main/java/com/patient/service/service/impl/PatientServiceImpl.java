package com.patient.service.service.impl;

import com.netflix.discovery.converters.Auto;
import com.patient.service.helper.PatientNotFoundException;
import com.patient.service.model.Patient;
import com.patient.service.repo.PatientRepository;
import com.patient.service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient addPatient(Patient patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    public List<Patient> getPatients() {
        return this.patientRepository.findAll();
    }

    @Override
    public Patient getPatient(Long patientId) throws Exception {
        Patient patient = this.patientRepository.findById(patientId).get();
        if(patient == null){
           //throw new PatientNotFoundException();
            throw new RuntimeException("Patient not found with this Id"); // it is not completed yet
        }
        return patient;
    }

    @Override
    public void deletePatient(Long patientId) {
        this.patientRepository.deleteById(patientId);
//        Patient patient = new Patient();
//        patient.setPatientId(patientId);
//        return this.patientRepository.delete(patient);
    }
}
