package com.urbanchic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerAddressDto {

    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String sellerId;
}
