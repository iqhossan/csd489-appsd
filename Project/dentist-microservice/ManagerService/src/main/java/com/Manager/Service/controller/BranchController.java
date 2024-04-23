package com.Manager.Service.controller;

import com.Manager.Service.dto.AddressDTO;
import com.Manager.Service.dto.BranchDTO;
import com.Manager.Service.model.Branch;
import com.Manager.Service.service.AddressFeignClient;
import com.Manager.Service.service.BranchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/branch")
@CrossOrigin("*")
public class BranchController {

    private final ModelMapper modelMapper;

    @Autowired
    private BranchService branchService;

    @Autowired
    private AddressFeignClient addressFeignClient;

    @Autowired
    public BranchController(BranchService branchService, ModelMapper modelMapper) {
        this.branchService = branchService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/")
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDto){
        Branch branch = modelMapper.map(branchDto, Branch.class);
        // insert the address data by using feign client
        AddressDTO addressDTO = addressFeignClient.createAddressData(branchDto.getAddress());
        Branch br = new Branch();
        if(addressDTO != null){
            //int s = addressDTO.getAddressId();
            branch.setAddressId(addressDTO.getAddressId());
            br = this.branchService.createBranch(branch);
        }
        return ResponseEntity.ok(modelMapper.map(br,BranchDTO.class));
    }

    @PutMapping("/")
    public ResponseEntity<BranchDTO> updateBranch(@RequestBody BranchDTO branchDto){
        Branch branch = modelMapper.map(branchDto, Branch.class);
        // insert the address data by using feign client
        AddressDTO addressDTO = addressFeignClient.updateAddressData(branchDto.getAddress());
        Branch br = new Branch();
        if(addressDTO != null){
            //int s = addressDTO.getAddressId();
            branch.setAddressId(addressDTO.getAddressId());
            br = this.branchService.updateBranch(branch);
        }
        return ResponseEntity.ok(modelMapper.map(br,BranchDTO.class));
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<?> getBranchById(@PathVariable("branchId") Long branchId) throws Exception{
        Branch branch = this.branchService.getBranchById(branchId);
        if(branch != null){
            BranchDTO branchDTO = modelMapper.map(branch,BranchDTO.class);
            AddressDTO addressDTO = addressFeignClient.getAddressDataById(branch.getAddressId());
            branchDTO.setAddress(addressDTO);
            return ResponseEntity.ok(branchDTO);
        }
        return ResponseEntity.ok("Data not found");
    }

    @GetMapping("/all")
    public ResponseEntity<List<BranchDTO>> getAllBranch(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false ) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false ) Integer pageSize
            ){
        Page<Branch> branch = this.branchService.getAllBranch(pageNumber, pageSize);
        //Sorting patient by lastname
        //branch.sort(Comparator.comparing(Branch::getBranchName).reversed());
        List<BranchDTO> branchDTOList = branch.stream()
                .map(br-> modelMapper.map(br, BranchDTO.class))
                .collect(Collectors.toList());
        List<BranchDTO> branchListWithAddress = new ArrayList<>();
        for(BranchDTO brdto:branchDTOList){
            System.out.println(brdto.getAddressId());
            brdto.setAddress(addressFeignClient.getAddressDataById(brdto.getAddressId()));
            branchListWithAddress.add(brdto);
        }
        return ResponseEntity.ok(branchListWithAddress);
    }


    @DeleteMapping("/{branchId}")
    public Boolean deleteBranch(@PathVariable("branchId") Long branchId){
        Branch branch = this.branchService.getBranchById(branchId);
        if(branch != null){
            Boolean add = addressFeignClient.deleteAddressDataById(branch.getAddressId());
            if(add){
                return this.branchService.deleteBranch(branchId);
            }
        }
        return false;
    }

}
