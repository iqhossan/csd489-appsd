package com.mock.test2.service;

import com.mock.test2.dto.ProductResponse;
import com.mock.test2.dto.ProductInput;
import com.mock.test2.dto.SupplierResponse;
import com.mock.test2.entity.Product;
import com.mock.test2.entity.Supplier;

import java.util.List;

public interface ProductService {

    ProductResponse updateProduct(Long productNo,ProductInput productResponse2);
    List<ProductResponse> getAllProducts();
    ProductResponse addProduct(ProductInput productInput);
}
