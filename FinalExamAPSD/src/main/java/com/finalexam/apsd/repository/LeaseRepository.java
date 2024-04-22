package com.finalexam.apsd.repository;

import com.finalexam.apsd.entity.Lease;
import com.finalexam.apsd.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaseRepository extends JpaRepository<Lease, Long> {


}
