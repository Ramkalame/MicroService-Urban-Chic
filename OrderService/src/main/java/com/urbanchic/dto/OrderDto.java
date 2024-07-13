package com.urbanchic.dto;

import com.urbanchic.entity.statusenum.OrderStatus;
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
public class OrderDto {

    @NotBlank(message = "product id is mandatory")
    private String productId;
    @NotBlank(message = "buyer id is mandatory")
    private String buyerId;
    @NotBlank(message = "buyer id is mandatory")
    private String sellerId;
    @NotNull(message = "payment id is mandatory")
    private Integer paymentId;
}
