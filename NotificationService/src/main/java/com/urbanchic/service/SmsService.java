package com.urbanchic.service;

import com.twilio.rest.api.v2010.account.Message;
import com.urbanchic.dto.OtpRequestDto;
import com.urbanchic.dto.OtpSmsResponseDto;
import com.urbanchic.external.PurchasedOrderDto;

public interface SmsService {

    OtpSmsResponseDto sendSms(OtpRequestDto otpRequestDto);
    void sendPurchaseOrderSms(PurchasedOrderDto purchasedOrderDto);

}
