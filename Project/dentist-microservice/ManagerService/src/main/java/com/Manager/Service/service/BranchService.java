package com.Manager.Service.service;

import com.Manager.Service.model.Branch;

import java.util.List;

public interface BranchService {

    Branch createBranch(Branch branch);
    Branch updateBranch(Branch branch);
    List<Branch> getAllBranch(Integer pageNumber, Integer pageSize);
    Branch getBranchById(Long branchId);
    boolean deleteBranch(Long branchId);
}
