package com.lab7.adsweb.service.Impl;

import com.lab7.adsweb.dto.AddressResponse;
import com.lab7.adsweb.dto.PatientInput;
import com.lab7.adsweb.dto.PatientResponse;
import com.lab7.adsweb.model.Address;
import com.lab7.adsweb.model.Patient;
import com.lab7.adsweb.repository.PatientRepository;
import com.lab7.adsweb.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repository;

    @Override
    public List<PatientResponse> GetAllPatient() {
        return this.repository.findAll(Sort.by("lastName")).stream()
                .map(p-> new PatientResponse(
                        p.getPatientId(), p.getFirstName(),p.getLastName(),
                        new AddressResponse(p.getPrimaryAddress().getAddressId(),
                                p.getPrimaryAddress().getStreet(),
                                p.getPrimaryAddress().getState(),
                                p.getPrimaryAddress().getCity(),
                                p.getPrimaryAddress().getZipcode())
                )).toList();
    }

    @Override
    public List<PatientResponse> getPatientById(Long patientId) {
        return this.repository.findById(patientId).stream().map(
                p-> new PatientResponse(p.getPatientId(),p.getFirstName(),p.getLastName(),new AddressResponse(
                        p.getPrimaryAddress().getAddressId(),p.getPrimaryAddress().getStreet(),
                        p.getPrimaryAddress().getState(),
                        p.getPrimaryAddress().getCity(),p.getPrimaryAddress().getZipcode())
                )).toList();
    }

    @Override
    public PatientResponse registerPatient(PatientInput patientInput) {

        Patient patient = new Patient(null,patientInput.getFirstName(),patientInput.getLastName(),
                new Address(null, patientInput.getStreet(),patientInput.getState(),patientInput.getCity(),
                        patientInput.getZipcode()));


        var savePatient =  this.repository.save(patient);

        return new PatientResponse(savePatient.getPatientId(),savePatient.getFirstName(),savePatient.getLastName(),
                new AddressResponse(savePatient.getPrimaryAddress().getAddressId(),
                        savePatient.getPrimaryAddress().getStreet(),
                        savePatient.getPrimaryAddress().getState(),
                        savePatient.getPrimaryAddress().getCity(),
                        savePatient.getPrimaryAddress().getZipcode()));
    }

    @Override
    public Patient updatePatient(Patient patientEdit) {

        Patient patient = new Patient(patientEdit.getPatientId(),
                patientEdit.getFirstName(),patientEdit.getLastName(),
                new Address(patientEdit.getPrimaryAddress().getAddressId(),
                        patientEdit.getPrimaryAddress().getStreet(),patientEdit.getPrimaryAddress().getState(),
                        patientEdit.getPrimaryAddress().getCity(),patientEdit.getPrimaryAddress().getZipcode()));
        return this.repository.save(patient);
    }

    @Override
    public void deletePatient(Long patientId) {
        this.repository.deleteById(patientId);
    }

    @Override
    public List<PatientInput> serachPatient(String queryString) {
        return null;
    }
}

