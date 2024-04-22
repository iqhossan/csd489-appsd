package com.mock.test2.service;

import com.mock.test2.dto.*;

import java.util.List;

public interface SupplierService {

    List<SupplierResponse> getListOfSuppliers();
    List<SupplierResponse> getListOfStarSuppliers();

    SupplierResponse addSupplier(SupplierInput supplierInput);

    List<StarSupplierDTO> starSupplier();
}
