package com.appointment.service.controller;

import com.appointment.service.dto.AppointmentDTO;
import com.appointment.service.model.Appointment;
import com.appointment.service.service.AppointmentService;
import com.appointment.service.service.DentistFeignClient;
import com.appointment.service.service.PatientFeignClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointment")
@CrossOrigin("*")
public class AppointmentController {

    private final ModelMapper modelMapper;
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DentistFeignClient dentistFeignClient;

    @Autowired
    private PatientFeignClient patientFeignClient;


    @Autowired
    public AppointmentController(AppointmentService appointmentService, ModelMapper modelMapper) {
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO){
        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
        appointment = this.appointmentService.createAppointment(appointment);
        return ResponseEntity.ok(modelMapper.map(appointment, AppointmentDTO.class));
    }

    @PutMapping("/")
    public ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody AppointmentDTO appointmentDto){
        Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
        appointment = this.appointmentService.updateAppointment(appointment);
        return ResponseEntity.ok(modelMapper.map(appointment, AppointmentDTO.class));
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable("appointmentId") Long appointmentId){
        Appointment appointment = this.appointmentService.getAppointmentById(appointmentId);
        return ResponseEntity.ok(modelMapper.map(appointment, AppointmentDTO.class));
    }

    @GetMapping("/")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointment(){
        List<Appointment> appointment = this.appointmentService.getAllAppointment();
        List<AppointmentDTO> appointmentDTOS = appointment.stream()
                .map(app -> modelMapper.map(app, AppointmentDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(appointmentDTOS);
    }

    @GetMapping("/doctor/patient/list")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentWithPatientDentist(){
        List<Appointment>appointment = this.appointmentService.getAllAppointment();
        List<AppointmentDTO> appointmentDTOS = appointment.stream()
                .map(appointment1 -> modelMapper.map(appointment1, AppointmentDTO.class))
                .map(appointmentDTO -> {
                    appointmentDTO.setDentistDTO(dentistFeignClient.getDentistData(appointmentDTO.getDentistId()));
                    appointmentDTO.setPatientDTO(patientFeignClient.getPatientData(appointmentDTO.getPatientId()));
                    return appointmentDTO;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(appointmentDTOS);
    }

    @GetMapping("/doctor/patient/request/list")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentWithPatientDentistRequest(){
        List<Appointment>appointment = this.appointmentService.getAllAppointment();
        List<AppointmentDTO> appointmentDTOS = appointment.stream()
                .map(appointment1 -> modelMapper.map(appointment1, AppointmentDTO.class))
                .map(appointmentDTO -> {
                    appointmentDTO.setDentistDTO(dentistFeignClient.getDentistData(appointmentDTO.getDentistId()));
                    appointmentDTO.setPatientDTO(patientFeignClient.getPatientData(appointmentDTO.getPatientId()));
                    if(appointmentDTO.getRequestId() > 0) {
                        appointmentDTO.setAppointmentRequestDTO(patientFeignClient.getRequestData(appointmentDTO.getRequestId()));
                    }
                    return appointmentDTO;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(appointmentDTOS);
    }

    @DeleteMapping("/{appointmentId}")
    public Boolean deleteAppointment(@PathVariable("appointmentId") Long appointmentId){
        return this.appointmentService.deleteAppointment(appointmentId);
    }

}
