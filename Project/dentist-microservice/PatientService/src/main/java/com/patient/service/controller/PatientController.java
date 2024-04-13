package com.patient.service.controller;

import com.netflix.discovery.converters.Auto;
import com.patient.service.dto.AddressDTO;
import com.patient.service.dto.PatientDTO;
import com.patient.service.model.Patient;
import com.patient.service.service.AddressFeignClient;
import com.patient.service.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patient")
@CrossOrigin("*")
public class PatientController {
    private final ModelMapper modelMapper;
    private PatientService patientService;

    @Autowired
    private AddressFeignClient addressFeignClient;

    @Autowired
    public PatientController(PatientService patientService, ModelMapper modelMapper) {
        this.patientService = patientService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/")
    public ResponseEntity<PatientDTO> addPatient(@RequestBody() PatientDTO patientDTO){
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        // insert the address data by using feign client
        AddressDTO addressDTO = addressFeignClient.createAddressData(patientDTO.getAddress());
        Patient pa = new Patient();
        if(addressDTO != null){
            //int s = addressDTO.getAddressId();
            patient.setAddressId(addressDTO.getAddressId());
            pa = this.patientService.addPatient(patient);
        }
        return ResponseEntity.ok(modelMapper.map(pa,PatientDTO.class));
    }

    @PutMapping("/")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody() PatientDTO patientDTO){
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        // insert the address data by using feign client
        AddressDTO addressDTO = addressFeignClient.updateAddressData(patientDTO.getAddress());
        Patient pa = new Patient();
        if(addressDTO != null){
            //int s = addressDTO.getAddressId();
            patient.setAddressId(addressDTO.getAddressId());
            pa = this.patientService.updatePatient(patient);
        }
        return ResponseEntity.ok(modelMapper.map(pa,PatientDTO.class));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getPatientById(@PathVariable("patientId") Long patientId) throws Exception{
        Patient patient = this.patientService.getPatient(patientId);
        if(patient !=null){
            PatientDTO patientDTO = modelMapper.map(patient,PatientDTO.class);
            AddressDTO addressDTO = addressFeignClient.getAddressDataById(patientId);
            patientDTO.setAddress(addressDTO);
            return ResponseEntity.ok(patientDTO);
        }
        return ResponseEntity.ok("Data not found");
    }

    @GetMapping("/")
    public ResponseEntity<List<PatientDTO>> getPatients(){
        List<Patient> patient = this.patientService.getPatients();
        //Sorting patient by lastname
        patient.sort(Comparator.comparing(Patient::getLastName));
        List<PatientDTO> patientDTOList = patient.stream()
                .map(patient1-> modelMapper.map(patient1, PatientDTO.class))
                .collect(Collectors.toList());
        List<PatientDTO> patientListWithAddress = new ArrayList<>();
        for(PatientDTO pt:patientDTOList){
            pt.setAddress(addressFeignClient.getAddressDataById(pt.getPatientId()));
            patientListWithAddress.add(pt);
        }
        return ResponseEntity.ok(patientListWithAddress);
    }

    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable("patientId") Long patientId){
        this.patientService.deletePatient(patientId);
    }







}
