package com.mock.test2.dto;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInput {

    private Long productNo;
    @NotBlank(message = "Product Name is required and cannot be null or empty string or blank spaces")
    private String name;
    private LocalDate dateSupplied;
    private int quantityInStock;
    @NotEmpty(message = "Unit Price is required and cannot be null or empty string")
    private double unitPrice;

    private SupplierInput supplier;


}
