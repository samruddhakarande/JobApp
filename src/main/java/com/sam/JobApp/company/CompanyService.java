package com.sam.JobApp.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    Company getCompanyById(long id);
    Company addCompany(Company company);
    Company updateCompany(long id, Company company);
    Boolean deleteCompany(long id);
}
