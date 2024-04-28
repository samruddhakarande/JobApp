package com.sam.JobApp.company.impl;

import com.sam.JobApp.company.Company;
import com.sam.JobApp.company.CompanyRepository;
import com.sam.JobApp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            company.setJobList(updatedCompany.getJobList());
            companyRepository.save(company);
            return company;
        }
        return null;
    }

    @Override
    public Boolean deleteCompany(long id) {
       if(companyRepository.existsById(id)) {
           companyRepository.deleteById(id);
           return true;
       }
       return false;
    }
}
