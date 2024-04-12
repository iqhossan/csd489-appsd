package com.patient.service.controller;

import com.netflix.discovery.converters.Auto;
import com.patient.service.model.AppointmentRequest;
import com.patient.service.model.Patient;
import com.patient.service.service.AppointmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
@CrossOrigin("*")
public class AppointmentRequestController {

    @Autowired
    private AppointmentRequestService appointmentRequestService;

    @PostMapping("/")
    public AppointmentRequest raiseRequest(@RequestBody AppointmentRequest appointmentRequest){
        return this.appointmentRequestService.addAppointmentReq(appointmentRequest);
    }

    @PutMapping("/")
    public AppointmentRequest updateRequest(@RequestBody AppointmentRequest appointmentRequest){
        return this.appointmentRequestService.updateAppointmentReq(appointmentRequest);
    }

    @GetMapping("/")
    public List<AppointmentRequest> getAllRequest(){
        return this.appointmentRequestService.getAllAppointmentRequest();
    }

    @GetMapping("/{requestId}")
    public AppointmentRequest getRequestById(@PathVariable("requestId") Long requestId){
        return this.appointmentRequestService.getAppointmentRequestById(requestId);
    }

    @DeleteMapping("/{requestId}")
    public void getDeleteRequestById(@PathVariable("requestId") Long requestId){
        this.appointmentRequestService.deleteAppointmentReq(requestId);
    }

    @GetMapping("/patient/{pid}")
    public List<AppointmentRequest> getAppointmentRequestsOfPatient(@PathVariable("pid") Long pid){
        Patient patient = new Patient();
        patient.setPatientId(pid);
        return this.appointmentRequestService.getAppointmentRequestsOfPatient(patient);
    }

}
