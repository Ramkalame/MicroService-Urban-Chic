package com.urbanchic.dto;

import com.urbanchic.entity.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private String paymentId;
    private String paymentOrderId;
    private String paymentSignature;
    private String orderId;
    private int amount;
    private String currency;
//    private String paymentStatus;
//    private String paymentMethod;
}
