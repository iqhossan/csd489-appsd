package com.finalexam.apsd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="property")

public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int propertyId;
    @NotNull
    private String propertyRef;
    private String city;
    private String state;
    @NotNull
    private double monthlyRentalRate;


    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Lease> leaseList;

}
