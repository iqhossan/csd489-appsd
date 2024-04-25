package com.mock.test2.service.Impl;

import com.mock.test2.dto.*;
import com.mock.test2.entity.Supplier;
import com.mock.test2.repository.SupplierRepository;
import com.mock.test2.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SupplierRepository repository;

    @Override
    public List<SupplierResponse> getListOfSuppliers() {
        List<Supplier> supplier = this.repository.findAll(Sort.by("name"));
//        List<SupplierResponse> res =  supplier.stream()
//                .map(s-> modelMapper.map(s,SupplierResponse.class)).collect(Collectors.toList());

        List<SupplierResponse> res = supplier.stream()
                .map(s->new SupplierResponse(s.getSupplierId(),s.getName(),s.getContactPhone())).toList();
        return res;
    }

    @Override
    public List<SupplierResponse> getListOfStarSuppliers() {
        return null;
    }

    @Override
    public SupplierResponse addSupplier(SupplierInput supplierInput) {

        Supplier res = modelMapper.map(supplierInput,Supplier.class);
        res = this.repository.save(res);
        return modelMapper.map(res,SupplierResponse.class);
    }

    @Override
    public List<StarSupplierDTO> starSupplier() {

        List<StarSupplierDTO> starSupplier = this.repository.findStarSupplier();
        List<StarSupplierDTO> starSupplier1 = new ArrayList<>();
        return starSupplier;
    }


}
