package com.urbanchic.service;

import com.urbanchic.dto.otp.SmsOtpRequestDto;
import com.urbanchic.dto.otp.VerifyOtpDto;

public interface SmsService {

    String sendOtpSms(SmsOtpRequestDto smsOtpRequestDto);
    boolean verifyOtp(VerifyOtpDto verifyOtpDto);

}
