package com.address.service;


import com.address.service.model.Address;
import com.address.service.repo.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class AddressRepositoryTests {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void SaveAddressTest(){
        Address address = new Address(1L,"1000 North","4th Street","IA","Fairfield",52557);
        address = this.addressRepository.save(address);
        // Find the Address by ID
       // List<Address> foundAddressOptional = addressRepository.findAll();
        Address foundAddress = addressRepository.findById(1L).get();
        //foundAddressOptional.forEach(System.out::println);
        //System.out.println(foundAddress.getAddressId());
        assertTrue(address.getAddressId()>0);
        assertThat(foundAddress.getState()).isEqualTo("IA");
    }

}
