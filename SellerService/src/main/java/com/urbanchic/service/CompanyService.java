package com.urbanchic.service;

import com.urbanchic.model.Company;
import com.urbanchic.modelDTO.CompanyDto;
import org.springframework.stereotype.Service;


public interface CompanyService {

    public Company addCompany(CompanyDto companyDto);
}
