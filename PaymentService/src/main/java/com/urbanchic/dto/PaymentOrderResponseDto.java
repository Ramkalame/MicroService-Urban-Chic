package com.urbanchic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrderResponseDto {

    private String orderId;
    private String status;
    private String currency;
    private String entity;
    private Integer amount;

}
