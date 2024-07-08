package com.urbanchic.service;

import com.urbanchic.dto.OtpRequestDto;
import com.urbanchic.dto.OtpVerificationDto;
import com.urbanchic.entity.Otp;

public interface OtpService {

    Otp getOtpByOtpNumber(String otpNumber);

    boolean verifyOtp(OtpVerificationDto otpVerificationDto);

    Otp sendOtp(OtpRequestDto otpRequestDto);

}
