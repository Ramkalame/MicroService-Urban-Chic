package com.urbanchic.modelDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDto {

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Company logo image is required")
    private String companyLogoImg;

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @NotBlank(message = "Company address is required")
    private String companyAddress;

    @NotBlank(message = "Company primary phone number is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String companyPrimaryPhoneNo;

    @NotBlank(message = "Company primary email is required")
    @Email(message = "Invalid email format")
    private String companyPrimaryEmail;

    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String companySecondaryPhoneNo;

    @Email(message = "Invalid email format")
    private String companySecondaryEmail;

    @NotBlank(message = "Seller ID is required")
    private String sellerId;
}
