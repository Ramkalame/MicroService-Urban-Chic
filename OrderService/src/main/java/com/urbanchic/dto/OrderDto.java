package com.urbanchic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @NotBlank(message = "buyer id is mandatory")
    private String buyerId;
    @NotBlank(message = "buyer id is mandatory")
    private String sellerId;
    @NotNull(message = "payment id is mandatory")
    private Integer paymentId;
    @NotNull(message = "product id is mandatory")
    private List<OrderedProductDto> productIdList;


}
