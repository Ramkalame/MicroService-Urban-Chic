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
public class SellerDto {


    @NotBlank(message = "Seller full name is required")
    private String sellerFullName;

    @NotBlank(message = "Seller email is required")
    @Email(message = "Invalid email format")
    private String sellerEmail;

    @NotBlank(message = "Seller primary mobile number is required")
    @Size(min = 10, max = 15, message = "Mobile number must be between 10 and 15 digits")
    private String sellerPrimaryMoNumber;

    @Email(message = "Invalid email format")
    private String sellerPrimaryEmail;

    @Size(min = 10, max = 15, message = "Mobile number must be between 10 and 15 digits")
    private String sellerSecondaryMoNumber;

    @Email(message = "Invalid email format")
    private String sellerSecondaryEmail;

    @NotBlank(message = "Seller address is required")
    private String sellerAddress;
}
