package com.mock.test2.dto;

import com.mock.test2.entity.Supplier;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long productNo;
    @NotBlank(message = "Product Name is required and cannot be null or empty string or blank spaces")
    private String name;
    private LocalDate dateSupplied;
    private int quantityInStock;
    @NotEmpty(message = "Unit Price is required and cannot be null or empty string")
    private double unitPrice;

    private SupplierResponse supplier;


}
