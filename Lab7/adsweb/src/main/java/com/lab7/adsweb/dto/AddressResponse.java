package com.lab7.adsweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private Long addressId;
    private String street;
    private String state;
    private String city;
    private int zipcode;
}
