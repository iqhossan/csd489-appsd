package com.mock.test2.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mock.test2.entity.Product;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponse {
    private Long supplierId;
    @NotBlank(message = "name should be required field")
    private String name;
    private String contactPhone;

    //private List<Product> product;


}
