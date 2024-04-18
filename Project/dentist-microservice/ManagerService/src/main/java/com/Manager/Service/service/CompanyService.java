package com.Manager.Service.service;

import com.Manager.Service.model.Company;

import java.util.List;

public interface CompanyService {

    Company createCompany(Company address);
    Company updateCompany(Company address);
    List<Company> getAllCompany();
    Company getCompanyById(Long companyId);
    boolean deleteCompany(Long companyId);
}
