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
public class AddressDto {

    private String houseNumber;
    @NotBlank(message = "street name is mandatory")
    private String streetName;
    private String landmark;
    @NotBlank(message = "city is mandatory")
    private String city;
    @NotBlank(message = "district is mandatory")
    private String district;
    @NotBlank(message = "state is mandatory")
    private String state;
    @NotBlank(message = "pin code is mandatory")
    private String pinCode;
    @NotBlank(message = "Address label is mandatory")
    private String addressLabel;

}
