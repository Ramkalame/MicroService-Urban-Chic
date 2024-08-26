package com.urbanchic.service.impl;

import com.urbanchic.entity.Company;
import com.urbanchic.dto.CompanyDto;
import com.urbanchic.repository.CompanyRepository;
import com.urbanchic.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company addCompany(CompanyDto companyDto) {

        Company company = Company.builder()
                .companyName(companyDto.getCompanyName())
                .companyLogoImg(companyDto.getCompanyLogoImg())
                .registrationNumber(companyDto.getRegistrationNumber())
                .companyAddress(companyDto.getCompanyAddress())
                .companyPrimaryPhoneNo(companyDto.getCompanyPrimaryPhoneNo())
                .companyPrimaryEmail(companyDto.getCompanyPrimaryEmail())
                .companySecondaryPhoneNo(companyDto.getCompanySecondaryPhoneNo())
                .companySecondaryEmail(companyDto.getCompanySecondaryEmail())
                .sellerId(companyDto.getSellerId())
                .build();

        return companyRepository.save(company);
    }
}
