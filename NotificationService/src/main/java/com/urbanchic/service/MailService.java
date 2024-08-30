package com.urbanchic.service;


import com.urbanchic.dto.otp.EmailOtpRequestDto;
import com.urbanchic.dto.otp.OtpResponseDto;

public interface MailService {

    OtpResponseDto sendOtpEmail(EmailOtpRequestDto emailOtpRequestDto);


}
