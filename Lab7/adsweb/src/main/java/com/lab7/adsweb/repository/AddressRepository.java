package com.lab7.adsweb.repository;

import com.lab7.adsweb.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
