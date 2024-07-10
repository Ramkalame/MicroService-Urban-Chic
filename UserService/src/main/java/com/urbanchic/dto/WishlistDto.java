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
public class WishlistDto {

    @NotBlank(message = "ProductId is mandatory")
    private String productId;
    @NotBlank(message = "Mobile No is mandatory")
    private String mobileNo;
}
