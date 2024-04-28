package com.sam.JobApp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companyList = companyService.getAllCompanies();
        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable long id) {
        Company company = companyService.getCompanyById(id);
        if(company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addcompany")
    public ResponseEntity<Company> addCompany(@RequestBody Company company){
        Company addedCompany = companyService.addCompany(company);
        return new ResponseEntity<>(addedCompany, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable long id, @RequestBody  Company company) {
        Company updatedCompany = companyService.updateCompany(id, company);
        if(updatedCompany != null) {
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable long id) {
        boolean deleteCompany = companyService.deleteCompany(id);
        if(deleteCompany) {
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
