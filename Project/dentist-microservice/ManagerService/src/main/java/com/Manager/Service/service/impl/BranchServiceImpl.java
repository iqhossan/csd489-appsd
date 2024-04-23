package com.Manager.Service.service.impl;

import com.Manager.Service.model.Branch;
import com.Manager.Service.repo.BranchRepository;
import com.Manager.Service.service.BranchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Arrays;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    private static final Logger logger = Logger.getLogger(BranchServiceImpl.class.getName());


    @Override
    public Branch createBranch(Branch branch) {
        return this.branchRepository.save(branch);
    }

    @Override
    public Branch updateBranch(Branch branch) {
        return this.branchRepository.save(branch);
    }

    @Override
    public Page<Branch> getAllBranch(Integer pageNumber,Integer pageSize) {
//        Pageable p = PageRequest.of(pageNumber,pageSize);
//        Page<Branch> pageBranch = this.branchRepository.findAll(p);
//        List<Branch> AllBranch = pageBranch.getContent();
//        AllBranch.stream().forEach(n->System.out.println(n));
//        return AllBranch;
//

        try {
            PageRequest pageRequest = PageRequest.of(pageNumber , pageSize); // Adjust pageNumber to 0-based index
            Page<Branch> pageBranch = this.branchRepository.findAll(pageRequest);
            List<Branch> allBranch = pageBranch.getContent();
            allBranch.forEach(branch -> logger.info("Fetched Branch: " + branch.toString()));
            return pageBranch;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching branches: " + e.getMessage(), e);
            throw new RuntimeException("Error fetching branches");
        }
    }

    @Override
    public Branch getBranchById(Long branchId) {
        return this.branchRepository.findById(branchId).get();
    }

    @Override
    public boolean deleteBranch(Long branchId) {
        this.branchRepository.deleteById(branchId);
        return true;
    }

}
