package com.Manager.Service.service;

import com.Manager.Service.model.Branch;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BranchService {

    Branch createBranch(Branch branch);
    Branch updateBranch(Branch branch);
    Page<Branch> getAllBranch(Integer pageNumber, Integer pageSize);
    Branch getBranchById(Long branchId);
    boolean deleteBranch(Long branchId);
}
