package com.address.Service.service;

import com.address.Service.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="PATIENT-SERVICE")
public interface PatientFeignClient {

//    @RequestMapping(value="/patient/{patientId}", method = RequestMethod.GET)
//    public PatientDTO getPatientData(@PathVariable("patientId") Long patientId);

}
