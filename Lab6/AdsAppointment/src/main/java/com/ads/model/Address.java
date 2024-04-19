package com.ads.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String street1;
    private String street2;
    private String city;
    private String state;
    @Column(length = 16)
    private int zipcode;

    @OneToOne(mappedBy = "primaryAddress")
    private Patient patient;

    @OneToOne(mappedBy = "primaryAddress")
    private Surgery surgery;

}
