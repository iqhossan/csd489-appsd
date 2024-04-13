package com.dentist.Service.controller;

import com.dentist.Service.dto.AppointmentDTO;
import com.dentist.Service.dto.DentistDTO;
import com.dentist.Service.dto.PatientDTO;
import com.dentist.Service.model.Dentist;
import com.dentist.Service.service.AppointmentFeignClient;
import com.dentist.Service.service.DentistService;
import com.dentist.Service.service.PatientFeignClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dentist")
@CrossOrigin("*")
public class DentistController {

    private final ModelMapper modelMapper;
    @Autowired
    private DentistService dentistService;

    @Autowired
    private AppointmentFeignClient appointmentFeignClient;

    @Autowired
    private PatientFeignClient patientFeignClient;


    @Autowired
    public DentistController(DentistService dentistService, ModelMapper modelMapper) {
        this.dentistService = dentistService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/")
    public ResponseEntity<DentistDTO> createDentist(@RequestBody DentistDTO dentistDTO){
        Dentist dentist = modelMapper.map(dentistDTO, Dentist.class);
        dentist = this.dentistService.registerDentist(dentist);
        return ResponseEntity.ok(modelMapper.map(dentist, DentistDTO.class));
    }

    @PutMapping("/")
    public ResponseEntity<DentistDTO> updateDentist(@RequestBody DentistDTO addressDto){
        Dentist dentist = modelMapper.map(addressDto, Dentist.class);
        dentist = this.dentistService.updateDentist(dentist);
        return ResponseEntity.ok(modelMapper.map(dentist, DentistDTO.class));
    }

    @GetMapping("/{dentistId}")
    public ResponseEntity<DentistDTO> getDentistById(@PathVariable("dentistId") Long dentistId){
        Dentist dentist = this.dentistService.getDentistById(dentistId);
        return ResponseEntity.ok(modelMapper.map(dentist, DentistDTO.class));
    }

    @GetMapping("/")
    public ResponseEntity<List<DentistDTO>> getAllDentist(){
        List<Dentist> dentist = this.dentistService.getAllDentist();
        List<DentistDTO> dentistDTOList = dentist.stream()
                .map(dentist1 -> modelMapper.map(dentist1, DentistDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dentistDTOList);
    }

    @GetMapping("/appointment/list")
    public ResponseEntity<List<DentistDTO>> getAllDentistWithAppointment(){
        List<Dentist> dentist = this.dentistService.getAllDentist();
        List<DentistDTO> dentistDTOList = dentist.stream()
                .map(dentist1 -> modelMapper.map(dentist1, DentistDTO.class))
                .map(dentistDTO -> {
                    dentistDTO.setAppointmentDTO(appointmentFeignClient.getAppointmentData(dentistDTO.getDentistId()));
                    return dentistDTO;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dentistDTOList);
    }

    @GetMapping("/appointment/patient/list")
    public ResponseEntity<List<DentistDTO>> getAllDentistAppointmentWithPatient(){
        List<Dentist> dentist = this.dentistService.getAllDentist();
        List<DentistDTO> dentistDTOList = dentist.stream()
                .map(dentist1 -> modelMapper.map(dentist1, DentistDTO.class))
                .map(dentistDTO -> {
                    dentistDTO.setAppointmentDTO(appointmentFeignClient.getAppointmentData(dentistDTO.getDentistId()));
                    return dentistDTO;
                })
                .collect(Collectors.toList());
        // get patient data based on appointment
        for(DentistDTO d:dentistDTOList){
            List<AppointmentDTO> a= d.getAppointmentDTO();
            PatientDTO an = patientFeignClient.getPatientData(a.get(0).getPatientId());
            d.getAppointmentDTO().get(0).setPatientDTO(an);
        }

        return ResponseEntity.ok(dentistDTOList);
    }

    @DeleteMapping("/{dentistId}")
    public Boolean deleteDentist(@PathVariable("dentistId") Long dentistId){
        return this.dentistService.deleteDentist(dentistId);
    }

}
