package com.mock.test2.repository;

import com.mock.test2.dto.StarSupplierDTO;
import com.mock.test2.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "SELECT supplierId,supplers.name,contactPhone,SUM(quantityInStock*unitPrice) as totalPrice, " +
            " count(productNo) as prdCount" +
            " FROM supplers INNER JOIN products on supplier_id = supplierId" +
            " group by supplierId,name,contactPhone " +
            " having SUM(quantityInStock*unitPrice)>100000 and count(productNo)>1 Order by supplers.name asc ",
            nativeQuery = true)
    List<StarSupplierDTO> findStarSupplier();
}
