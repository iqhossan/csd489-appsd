package com.mock.test2.service.Impl;

import com.mock.test2.dto.ProductResponse;
import com.mock.test2.dto.ProductInput;
import com.mock.test2.dto.SupplierInput;
import com.mock.test2.dto.SupplierResponse;
import com.mock.test2.entity.Product;
import com.mock.test2.entity.Supplier;
import com.mock.test2.repository.ProductRepository;
import com.mock.test2.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private ProductRepository repository;
    @Override
    public ProductResponse updateProduct(Long productNo,ProductInput productInput) {

        Product product = this.repository.findById(productNo).get();
        if(product != null){
            product = new Product(productInput.getProductNo(),productInput.getName(),productInput.getDateSupplied(),
                    productInput.getQuantityInStock(),productInput.getUnitPrice(),
                    new Supplier(product.getSupplier().getSupplierId(),
                            productInput.getSupplier().getName(),
                            productInput.getSupplier().getContactPhone()));
        }

        product = this.repository.save(product);
        return new ProductResponse(product.getProductNo(),product.getName(),product.getDateSupplied(),
                product.getQuantityInStock(),product.getUnitPrice(),
                new SupplierResponse(product.getSupplier().getSupplierId(),
                        product.getSupplier().getName(),
                        product.getSupplier().getContactPhone()));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return this.repository.findAll().stream().map(
                p->new ProductResponse(p.getProductNo(),
                        p.getName(),
                        p.getDateSupplied(),
                        p.getQuantityInStock(),
                        p.getUnitPrice(),
                        new SupplierResponse(p.getSupplier().getSupplierId(),
                                p.getSupplier().getName(),
                                p.getSupplier().getContactPhone()))
        ).toList();
    }

    @Override
    public ProductResponse addProduct(ProductInput productInput) {
        Product prd = modelMapper.map(productInput,Product.class);
        prd = this.repository.save(prd);
        return modelMapper.map(prd,ProductResponse.class);
    }


}
