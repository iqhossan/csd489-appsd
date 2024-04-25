package com.patient.service.service.impl;

import com.netflix.discovery.converters.Auto;
import com.patient.service.helper.PatientNotFoundException;
import com.patient.service.model.Patient;
import com.patient.service.repo.PatientRepository;
import com.patient.service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    private static final Logger logger = Logger.getLogger(PatientServiceImpl.class.getName());

    @Override
    public Patient addPatient(Patient patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    public List<Patient> getPatients(Integer pageNumber,Integer pageSize) {
        try {
            Sort.Order sortOrder = Sort.Order.desc("patientId"); // Sorting by 'patientId' field in descending order
            Sort sort = Sort.by(sortOrder);
            PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sort); // Adjust pageNumber to 0-based index
            Page<Patient> pagePatient = this.patientRepository.findAll(pageRequest);
            List<Patient> allPatient = pagePatient.getContent();

            // Log essential patient information without causing recursion
            allPatient.forEach(p -> logger.info("Fetched patient - ID: " + p.getPatientId() + ", Name: " + p.getFirstName() + " " + p.getLastName()));
            return allPatient;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching patients: " + e.getMessage(), e);
            throw new RuntimeException("Error fetching patients");
        }
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
