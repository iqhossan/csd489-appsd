package com.mock.test2.controller;


import com.mock.test2.dto.ProductInput;
import com.mock.test2.dto.ProductResponse;
import com.mock.test2.dto.SupplierInput;
import com.mock.test2.dto.SupplierResponse;
import com.mock.test2.entity.Product;
import com.mock.test2.entity.Supplier;
import com.mock.test2.service.ProductService;
import com.mock.test2.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;



    @PostMapping("/")
    ResponseEntity<ProductResponse> addProducts(@RequestBody ProductInput productInput ){
        ProductResponse res = this.productService.addProduct(productInput);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{productNo}")
    ResponseEntity<ProductResponse> updateProducts(@PathVariable("productNo") Long productNo, @RequestBody ProductInput productInput){
        ProductResponse res = this.productService.updateProduct(productNo,productInput);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/")
    ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> res = this.productService.getAllProducts();
        return ResponseEntity.ok(res);
    }
}
