package com.lab7.adsweb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String street;
    private String state;
    private String city;
    @Column(length = 16)
    private int zipcode;


    @OneToOne(mappedBy = "primaryAddress")
    private Patient patient;

    public Address(Long addressId, String street, String state, String city, int zipcode) {
        this.addressId = addressId;
        this.street = street;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
    }
}
