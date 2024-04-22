package com.mock.test2.controller;

import com.mock.test2.dto.StarSupplierDTO;
import com.mock.test2.dto.SupplierInput;
import com.mock.test2.dto.SupplierResponse;
import com.mock.test2.service.SupplierService;
import org.hibernate.sql.ast.tree.expression.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/")
    ResponseEntity<SupplierResponse> addSupplier(@RequestBody SupplierInput supplierInput){
        SupplierResponse response= this.supplierService.addSupplier(supplierInput);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    ResponseEntity<List<SupplierResponse>> getListOfSupplier(){
        return ResponseEntity.ok(this.supplierService.getListOfSuppliers());
    }

    @GetMapping("/star")
    ResponseEntity<List<StarSupplierDTO>> getStarSupplier(){

        List<StarSupplierDTO> s = this.supplierService.starSupplier();

        return ResponseEntity.ok(s);
    }



}
