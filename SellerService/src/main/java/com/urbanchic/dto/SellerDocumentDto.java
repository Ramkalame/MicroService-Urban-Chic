package com.urbanchic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerDocumentDto {

    @NotBlank(message = "company name is required")
    private String companyName;
    @NotBlank(message = "company logo is required")
    private String companyLogoUrl;
    @NotNull(message = "company address is required")
    private SellerAddressDto sellerAddress;
    @NotNull(message = "company logo public id is required")
    private String companyLogoPublicId;
    @NotBlank(message = "GST number is required")
    private String gstNumber;
    @NotBlank(message = "PAN number is required")
    private String panNumber;
    @NotBlank(message = "Account Number is required")
    private String accountNumber;
    @NotBlank(message = "IFSC code is mandatory")
    private String ifscCode;
    @NotBlank(message = "seller Id is mandatory")
    private String sellerId;

}
