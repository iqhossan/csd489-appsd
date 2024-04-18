package com.address.Service.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="PATIENT-SERVICE")
public interface PatientFeignClient {

//    @RequestMapping(value="/patient/{patientId}", method = RequestMethod.GET)
//    public PatientDTO getPatientData(@PathVariable("patientId") Long patientId);

}
