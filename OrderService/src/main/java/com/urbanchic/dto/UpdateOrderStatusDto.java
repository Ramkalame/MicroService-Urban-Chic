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
public class UpdateOrderStatusDto {

    @NotBlank(message = "order id is mandatory")
    private String orderId;
    @NotBlank(message = "order status is mandatory")
    private String orderStatus;
}
