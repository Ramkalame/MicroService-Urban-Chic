package com.urbanchic.service;


import com.urbanchic.dto.otp.EmailOtpRequestDto;

public interface MailService {

    String sendOtpEmail(EmailOtpRequestDto emailOtpRequestDto);


}
