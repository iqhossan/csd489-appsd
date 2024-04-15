package com.appointment.service.service;

import com.appointment.service.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="MANAGER-SERVICE")
public interface BranchFeignClient {
    @RequestMapping(value="/branch/{branchId}", method = RequestMethod.GET)
    public PatientDTO getBranchData(@PathVariable("branchId") int branchId);
}
