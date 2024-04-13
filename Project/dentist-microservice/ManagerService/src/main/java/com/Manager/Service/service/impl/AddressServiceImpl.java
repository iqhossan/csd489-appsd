package com.address.Service.service.impl;

import com.address.Service.model.Address;
import com.address.Service.repo.AddressRepository;
import com.address.Service.service.AddressService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address) {
        return this.addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        return this.addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddress() {
        return this.addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long addressId) {
        return this.addressRepository.findById(addressId).get();
    }

    @Override
    public boolean deleteAddress(Long addressId) {
        this.addressRepository.deleteById(addressId);
        return true;
    }
}
