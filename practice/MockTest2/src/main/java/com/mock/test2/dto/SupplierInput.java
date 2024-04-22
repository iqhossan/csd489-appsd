package com.mock.test2.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierInput {
    private Long supplierId;
    @NotBlank(message = "name should be required field")
    private String name;
    private String contactPhone;

    private List<ProductInput> product;
}
