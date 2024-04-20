package com.Manager.Service.repo;

import com.Manager.Service.model.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}
