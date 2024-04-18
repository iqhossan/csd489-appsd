package com.Manager.Service.service.impl;

import com.Manager.Service.model.Branch;
import com.Manager.Service.repo.BranchRepository;
import com.Manager.Service.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Branch createBranch(Branch branch) {
        return this.branchRepository.save(branch);
    }

    @Override
    public Branch updateBranch(Branch branch) {
        return this.branchRepository.save(branch);
    }

    @Override
    public List<Branch> getAllBranch() {
        return this.branchRepository.findAll();
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
