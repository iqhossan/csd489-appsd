package com.Manager.Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {
    private Long branchId;
    private String branchName;
    private String telephoneNo;
    private Long companyId;
    private Long addressId;
    private AddressDTO address;
}
