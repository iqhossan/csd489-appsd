package com.appointment.service.service;

import com.appointment.service.dto.DentistDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="DENTIST-SERVICE")
public interface DentistFeignClient {
    @RequestMapping(value="/dentist/{dentistId}", method = RequestMethod.GET)
    public DentistDTO getDentistData(@PathVariable("dentistId") int dentistId);

}
