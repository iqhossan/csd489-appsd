package com.Manager.Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long addressId;
    private String street1;
    private String street2;
    private String state;
    private String city;
    private Long zipcode;
    private CompanyDTO companyDto;
}
