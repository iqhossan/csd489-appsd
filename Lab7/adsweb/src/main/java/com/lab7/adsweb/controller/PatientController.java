package com.lab7.adsweb.controller;

import com.lab7.adsweb.dto.PatientInput;
import com.lab7.adsweb.dto.PatientResponse;
import com.lab7.adsweb.model.Patient;
import com.lab7.adsweb.service.PatientService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @PostMapping("/")
    ResponseEntity<PatientResponse> registerPatient(@RequestBody PatientInput patientInput){
        PatientResponse a = this.patientService.registerPatient(patientInput);
        return ResponseEntity.ok(a);
    }

    @PutMapping
    ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
        Patient data = this.patientService.updatePatient(patient);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/")
    ResponseEntity<List<PatientResponse>> getAllPatient(){
        List<PatientResponse> data= this.patientService.GetAllPatient();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{patientId}")
    ResponseEntity<List<PatientResponse>> getPatientById(@PathVariable("patientId") Long patientId){
         List<PatientResponse> response = this.patientService.getPatientById(patientId);
         return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable("patientId") Long patientId){
        this.patientService.deletePatient(patientId);
    }
}
