package com.Manager.Service.service.impl;

import com.Manager.Service.model.Company;
import com.Manager.Service.repo.CompanyRepository;
import com.Manager.Service.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company createCompany(Company address) {
        return this.companyRepository.save(address);
    }

    @Override
    public Company updateCompany(Company address) {
        return this.companyRepository.save(address);
    }

    @Override
    public List<Company> getAllCompany() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return this.companyRepository.findById(companyId).get();
    }
    @Override
    public boolean deleteCompany(Long companyId) {
        this.companyRepository.deleteById(companyId);
        return true;
    }
}
