package com.urbanchic.service;

import com.urbanchic.dto.PaymentDto;
import com.urbanchic.dto.PaymentOrderResponseDto;
import com.urbanchic.entity.Payment;

public interface PaymentService {

    PaymentOrderResponseDto createOrder(double amount);
    Payment addPaymentDetails(PaymentDto paymentDto);
}
