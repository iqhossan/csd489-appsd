package com.patient.service.controller;

import com.netflix.discovery.converters.Auto;
import com.patient.service.dto.AddressDTO;
import com.patient.service.dto.PatientDTO;
import com.patient.service.model.Patient;
import com.patient.service.service.AddressFeignClient;
import com.patient.service.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patient")
@CrossOrigin("*")
public class PatientController {
    private final ModelMapper modelMapper;
    private PatientService patientService;

    @Autowired
    private AddressFeignClient addressFeignClient;

    @Autowired
    public PatientController(PatientService patientService, ModelMapper modelMapper) {
        this.patientService = patientService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/")
    public ResponseEntity<PatientDTO> addPatient(@RequestBody() PatientDTO patientDTO){
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        // insert the address data by using feign client
        AddressDTO addressDTO = addressFeignClient.createAddressData(patientDTO.getAddress());
        Patient pa = new Patient();
        if(addressDTO != null){
            //int s = addressDTO.getAddressId();
            patient.setAddressId(addressDTO.getAddressId());
            pa = this.patientService.addPatient(patient);
        }
        return ResponseEntity.ok(modelMapper.map(pa,PatientDTO.class));
    }

    @PutMapping("/")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody() PatientDTO patientDTO){
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        // insert the address data by using feign client
        AddressDTO addressDTO = addressFeignClient.updateAddressData(patientDTO.getAddress());
        Patient pa = new Patient();
        if(addressDTO != null){
            //int s = addressDTO.getAddressId();
            patient.setAddressId(addressDTO.getAddressId());
            pa = this.patientService.updatePatient(patient);
        }
        return ResponseEntity.ok(modelMapper.map(pa,PatientDTO.class));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getPatientById(@PathVariable("patientId") Long patientId) throws Exception{
        Patient patient = this.patientService.getPatient(patientId);
        if(patient !=null){
            PatientDTO patientDTO = modelMapper.map(patient,PatientDTO.class);
            AddressDTO addressDTO = addressFeignClient.getAddressDataById(patientDTO.getAddress().getAddressId());
            patientDTO.setAddress(addressDTO);
            return ResponseEntity.ok(patientDTO);
        }
        return ResponseEntity.ok("Data not found");
    }

    @GetMapping("/page")
    public ResponseEntity<List<PatientDTO>> getPatients(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false ) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false ) Integer pageSize
        ){
        List<PatientDTO> patient = this.patientService.getPatients(pageNumber, pageSize).stream()
                .map(p->new PatientDTO(p.getPatientId(),p.getFirstName(),p.getLastName(),
                        p.getPhoneNo(),
                        p.getEmail(),p.getDues(),
                        new AddressDTO(p.getAddressId()))).toList();
        //Sorting patient by lastname
        //patient.sort(Comparator.comparing(Patient::getLastName));

        List<PatientDTO> patientListWithAddress = new ArrayList<>();
        for(PatientDTO pt:patient){
            pt.setAddress(addressFeignClient.getAddressDataById(pt.getAddress().getAddressId()));
            patientListWithAddress.add(pt);
        }
        return ResponseEntity.ok(patientListWithAddress);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientDTO>> getPatientsExcludePagination(){
        List<PatientDTO> patient = this.patientService.getPatientsExcludePagination().stream()
                .map(p->new PatientDTO(p.getPatientId(),p.getFirstName(),p.getLastName(),
                        p.getPhoneNo(),
                        p.getEmail(),p.getDues(),
                        new AddressDTO(p.getAddressId()))).toList();

        List<PatientDTO> patientListWithAddress = new ArrayList<>();
        for(PatientDTO pt:patient){
            pt.setAddress(addressFeignClient.getAddressDataById(pt.getAddress().getAddressId()));
            patientListWithAddress.add(pt);
        }
        return ResponseEntity.ok(patientListWithAddress);
    }

    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable("patientId") Long patientId){
        this.patientService.deletePatient(patientId);
    }







}
