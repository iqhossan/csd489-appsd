package com.address.Service.controller;

import com.address.Service.model.Address;
import com.address.Service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@CrossOrigin("*")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/")
    public ResponseEntity<Address> createAddress(@RequestBody Address address){
        Address address1 = this.addressService.createAddress(address);
        return ResponseEntity.ok(address1);
    }

    @PutMapping("/")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address){
        Address address1 = this.addressService.createAddress(address);
        return ResponseEntity.ok(address1);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable("addressId") Long addressId){
        Address address = this.addressService.getAddressById(addressId);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/")
    public ResponseEntity<List<Address>> getAllAddress(){
        List<Address> address = this.addressService.getAllAddress();
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{addressId}")
    public Boolean deleteAddress(@PathVariable("addressId") Long addressId){
        return this.addressService.deleteAddress(addressId);
    }

}
