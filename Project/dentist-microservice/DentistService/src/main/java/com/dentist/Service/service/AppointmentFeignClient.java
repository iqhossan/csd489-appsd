package com.dentist.Service.service;

import com.dentist.Service.dto.AppointmentDTO;
import com.dentist.Service.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="APPOINTMENT-SERVICE")
public interface AppointmentFeignClient {
    @RequestMapping(value="/appointment/{appointmentId}", method = RequestMethod.GET)
    public List<AppointmentDTO> getAppointmentData(@PathVariable("appointmentId") Long appointmentId);

}
