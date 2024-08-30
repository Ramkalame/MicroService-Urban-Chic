package com.urbanchic.service;

import com.urbanchic.dto.otp.SmsOtpRequestDto;
import com.urbanchic.dto.otp.OtpResponseDto;

public interface SmsService {

    OtpResponseDto sendOtpSms(SmsOtpRequestDto smsOtpRequestDto);

}
