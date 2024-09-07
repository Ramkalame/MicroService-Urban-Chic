package com.urbanchic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerAddressDto {

    @NotBlank(message = "seller street is required")
    private String street;
    @NotBlank(message = "seller city is required")
    private String city;
    @NotBlank(message = "seller state is required")
    private String state;
    @NotBlank(message = "seller country is required")
    private String country;
    @NotBlank(message = "seller postal code is required")
    private String postalCode;
    @NotBlank(message = "seller id is required")
    private String sellerId;
}
