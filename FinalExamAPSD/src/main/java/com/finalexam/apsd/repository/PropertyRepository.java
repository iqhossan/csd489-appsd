package com.finalexam.apsd.repository;

import com.finalexam.apsd.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

    List<Property> findPropertyByStateOrderByMonthlyRentalRateAsc(String state);

    @Query(value = "select p from Property p where p.state = :state order by p.monthlyRentalRate asc")
    List<Property> getPropertyUsingStae(String state);


}
