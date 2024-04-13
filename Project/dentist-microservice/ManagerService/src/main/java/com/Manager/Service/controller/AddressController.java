package com.address.Service.controller;

import com.address.Service.dto.AddressDTO;
import com.address.Service.model.Address;
import com.address.Service.service.AddressService;
import com.address.Service.service.PatientFeignClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
@CrossOrigin("*")
public class AddressController {

    private final ModelMapper modelMapper;
    @Autowired
    private AddressService addressService;

    @Autowired
    private PatientFeignClient patientFeignClient;

    @Autowired
    public AddressController(AddressService addressService, ModelMapper modelMapper) {
        this.addressService = addressService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDto){
        Address address = modelMapper.map(addressDto, Address.class);
        address = this.addressService.createAddress(address);
        return ResponseEntity.ok(modelMapper.map(address, AddressDTO.class));
    }

    @PutMapping("/")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDto){
        Address address = modelMapper.map(addressDto, Address.class);
        address = this.addressService.updateAddress(address);
        return ResponseEntity.ok(modelMapper.map(address, AddressDTO.class));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable("addressId") Long addressId){
        Address address = this.addressService.getAddressById(addressId);
        return ResponseEntity.ok(modelMapper.map(address, AddressDTO.class));
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressDTO>> getAllAddress(){
        List<Address> address = this.addressService.getAllAddress();
        List<AddressDTO> addressDTOList = address.stream()
                .map(address1 -> modelMapper.map(address1, AddressDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(addressDTOList);
    }

    @GetMapping("/patient/list")
    public ResponseEntity<List<AddressDTO>> getAllAddressWithPatient(){
        List<Address> addresses = this.addressService.getAllAddress();
        List<AddressDTO> addressDTOList = addresses.stream()
                .map(address1 -> modelMapper.map(address1, AddressDTO.class))
                .collect(Collectors.toList());
//        List<AddressDTO> AddressListWithPatient = new ArrayList<>();
//        for(AddressDTO address:addressDTOList){
//            address.setPatientDto(patientFeignClient.getPatientData(address.getAddressId()));
//            AddressListWithPatient.add(address);
//        }
        return ResponseEntity.ok(addressDTOList);
    }

    @DeleteMapping("/{addressId}")
    public Boolean deleteAddress(@PathVariable("addressId") Long addressId){
        return this.addressService.deleteAddress(addressId);
    }

}
