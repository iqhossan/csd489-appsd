package com.Manager.Service.controller;

import com.Manager.Service.dto.AddressDTO;
import com.Manager.Service.dto.BranchDTO;
import com.Manager.Service.dto.CompanyDTO;
import com.Manager.Service.model.Company;
import com.Manager.Service.service.AddressFeignClient;
import com.Manager.Service.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/company")
@CrossOrigin("*")
public class CompanyController {

    private final ModelMapper modelMapper;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AddressFeignClient addressFeignClient;

    @Autowired
    public CompanyController(CompanyService companyService, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/")
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDto){
        Company company = modelMapper.map(companyDto, Company.class);
        // insert the address data by using feign client
        AddressDTO addressDTO = addressFeignClient.createAddressData(companyDto.getAddress());
        Company com = new Company();
        if(addressDTO != null){
            //int s = addressDTO.getAddressId();
            company.setAddressId(addressDTO.getAddressId());
            com = this.companyService.createCompany(company);
        }
        return ResponseEntity.ok(modelMapper.map(com,CompanyDTO.class));
    }

    @PutMapping("/")
    public ResponseEntity<CompanyDTO> updateCompany(@RequestBody CompanyDTO companyDto){
        Company company = modelMapper.map(companyDto, Company.class);
        // insert the address data by using feign client
        AddressDTO addressDTO = addressFeignClient.updateAddressData(companyDto.getAddress());
        Company com = new Company();
        if(addressDTO != null){
            //int s = addressDTO.getAddressId();
            company.setAddressId(addressDTO.getAddressId());
            com = this.companyService.updateCompany(company);
        }
        return ResponseEntity.ok(modelMapper.map(com,CompanyDTO.class));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<?> getCompanyById(@PathVariable("companyId") Long companyId) throws Exception{
        Company company = this.companyService.getCompanyById(companyId);
        if(company !=null){
            CompanyDTO companyDTO = modelMapper.map(company,CompanyDTO.class);
            AddressDTO addressDTO = addressFeignClient.getAddressDataById(company.getAddressId());
            companyDTO.setAddress(addressDTO);
            return ResponseEntity.ok(companyDTO);
        }
        return ResponseEntity.ok("Data not found");
    }

    @GetMapping("/")
    public ResponseEntity<List<CompanyDTO>> getAllBranch(){
        List<Company> company = this.companyService.getAllCompany();
        //Sorting company by lastname
        company.sort(Comparator.comparing(Company::getCompanyName).reversed());
        List<CompanyDTO> companyDTOList = company.stream()
                .map(br-> modelMapper.map(br, CompanyDTO.class))
                .collect(Collectors.toList());
        List<CompanyDTO> branchListWithAddress = new ArrayList<>();
        for(CompanyDTO comdto:companyDTOList){
            comdto.setAddress(addressFeignClient.getAddressDataById(comdto.getAddressId()));
            branchListWithAddress.add(comdto);
        }
        return ResponseEntity.ok(branchListWithAddress);
    }

    @DeleteMapping("/{companyId}")
    public Boolean deleteCompany(@PathVariable("companyId") Long companyId){
        return this.companyService.deleteCompany(companyId);
    }

}
