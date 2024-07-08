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
public class CartDto {

    @NotBlank(message = "Product id is mandatory")
    private String productId;
    @NotBlank(message = "Mobile No. is mandatory")
    private String mobileNo;
    private Integer productQuantity;
}
