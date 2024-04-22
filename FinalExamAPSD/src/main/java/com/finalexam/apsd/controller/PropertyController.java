package com.finalexam.apsd.controller;

import com.finalexam.apsd.dto.PropertyDTO;
import com.finalexam.apsd.dto.PropertyResponse;
import com.finalexam.apsd.dto.RevenueResponse;
import com.finalexam.apsd.entity.Property;
import com.finalexam.apsd.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/{state}")
    ResponseEntity<List<PropertyDTO>> getAllProperites(@PathVariable("state") String state){
        List<PropertyDTO> list =  this.propertyService.getPropertiesByState(state);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/list/{state}")
    ResponseEntity<List<PropertyDTO>> getAllProperty(@PathVariable("state") String state){
        List<PropertyDTO> list =  this.propertyService.getAllProperties();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/using/{state}")
    ResponseEntity<List<PropertyDTO>> getPropertyUsingStae(@PathVariable("state") String state){
        List<PropertyDTO> list =  this.propertyService.getPropertiesByState(state);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/rev/{state}")
    ResponseEntity<RevenueResponse> getRevenueByState(@PathVariable("state") String state){
        RevenueResponse list =  this.propertyService.getTotalRevenue(state);
        return ResponseEntity.ok(list);
    }

}
