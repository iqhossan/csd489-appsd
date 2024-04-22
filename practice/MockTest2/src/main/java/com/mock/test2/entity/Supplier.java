package com.mock.test2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="supplers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    @NotBlank(message = "name should be required field")
    private String name;
    private String contactPhone;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> product;

    public Supplier(Long supplierId, String name, String contactPhone) {
        this.supplierId = supplierId;
        this.name = name;
        this.contactPhone = contactPhone;
    }
}
