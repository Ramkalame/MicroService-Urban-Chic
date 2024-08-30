package com.urbanchic.controller;

import com.urbanchic.dto.otp.EmailOtpRequestDto;
import com.urbanchic.dto.otp.SmsOtpRequestDto;
import com.urbanchic.dto.otp.OtpResponseDto;
import com.urbanchic.service.MailService;
import com.urbanchic.service.SmsService;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final SmsService smsService;
    private final MailService mailService;

    @PostMapping("/otp/sms")
    public ResponseEntity<ApiResponse<OtpResponseDto>> sendOtpSms(@RequestBody SmsOtpRequestDto smsOtpRequestDto) {
        OtpResponseDto responseData = smsService.sendOtpSms(smsOtpRequestDto);

        ApiResponse<OtpResponseDto> apiResponse = ApiResponse.<OtpResponseDto>builder()
                .data(responseData)
                .message("Otp sent to "+responseData.getTo())
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/otp/email")
    public ResponseEntity<ApiResponse<OtpResponseDto>> sendOtpMail(@RequestBody EmailOtpRequestDto emailOtpRequestDto) {
        OtpResponseDto responseData = mailService.sendOtpEmail(emailOtpRequestDto);

        ApiResponse<OtpResponseDto> apiResponse = ApiResponse.<OtpResponseDto>builder()
                .data(responseData)
                .message("E-Mail sent to "+responseData.getTo())
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


}
