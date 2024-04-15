package com.appointment.service.service;

import com.appointment.service.dto.AppointmentRequestDTO;
import com.appointment.service.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="PATIENT-SERVICE")
public interface PatientFeignClient {
    @RequestMapping(value="/patient/{patientId}", method = RequestMethod.GET)
    public PatientDTO getPatientData(@PathVariable("patientId") int patientId);

    @RequestMapping(value="/request/{requestId}", method = RequestMethod.GET)
    public AppointmentRequestDTO getRequestData(@PathVariable("requestId") int requestId);
}
