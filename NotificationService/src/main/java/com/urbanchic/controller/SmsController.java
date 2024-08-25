package com.urbanchic.controller;

import com.twilio.rest.api.v2010.account.Message;
import com.urbanchic.dto.OtpRequestDto;
import com.urbanchic.dto.OtpSmsResponseDto;
import com.urbanchic.service.impl.SmsServiceImpl;
import com.urbanchic.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/sms")
@RequiredArgsConstructor
public class SmsController {

    private  final SmsServiceImpl smsService;

    @PostMapping("/send/otp")
    public ResponseEntity<ApiResponse<OtpSmsResponseDto>> sendOtp(@RequestBody OtpRequestDto otpRequestDto) {
        OtpSmsResponseDto responseData = smsService.sendSms(otpRequestDto);
        ApiResponse<OtpSmsResponseDto> apiResponse = ApiResponse.<OtpSmsResponseDto>builder()
                .data(responseData)
                .message("Sms sent successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


}
