package com.urbanchic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "company_collecion")
public class Company {

    @Id
    private String companyId;
    private String companyName;
    private String companyLogoImg;
    private String registrationNumber;
    private String companyAddress;
    private String companyPrimaryPhoneNo;
    private String companyPrimaryEmail;
    private String companySecondaryPhoneNo;
    private String companySecondaryEmail;
    private String sellerId;
}
