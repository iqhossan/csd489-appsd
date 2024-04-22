package com.finalexam.apsd.controller;

import com.finalexam.apsd.dto.LeaseResponse;
import com.finalexam.apsd.dto.LeaseInput;
import com.finalexam.apsd.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lease")
public class LeaseController {

    @Autowired
    private LeaseService leaseService;

    @GetMapping("/list")
    ResponseEntity<List<LeaseResponse>> getAllLease(){
        List<LeaseResponse> list =  this.leaseService.getAllLease();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add/{propertyId}")
    ResponseEntity<LeaseResponse> addLease(@PathVariable("propertyId") int propertyId, @RequestBody LeaseInput leaseInput){
        LeaseResponse data =  this.leaseService.addLease(propertyId,leaseInput);
        return ResponseEntity.ok(data);
    }
}
