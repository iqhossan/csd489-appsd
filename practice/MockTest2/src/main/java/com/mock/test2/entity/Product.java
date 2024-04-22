package com.mock.test2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @NotNull
    @Column(unique = true)
    private Long productNo;
    @NotBlank(message = "Product Name is required and cannot be null or empty string or blank spaces")
    private String name;

    private LocalDate dateSupplied;
    private int quantityInStock;

    @NotNull
    private double unitPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    public Product(Long productNo, String name, LocalDate dateSupplied, int quantityInStock, double unitPrice) {
        this.productNo = productNo;
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }
}
