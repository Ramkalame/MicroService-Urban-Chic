package com.urbanchic.controller;

import com.urbanchic.dto.otp.EmailOtpRequestDto;
import com.urbanchic.dto.otp.SmsOtpRequestDto;
import com.urbanchic.dto.otp.VerifyOtpDto;
import com.urbanchic.service.MailService;
import com.urbanchic.service.SmsService;
import com.urbanchic.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final SmsService smsService;
    private final MailService mailService;

    @PostMapping("/otp/sms")
    public ResponseEntity<ApiResponse<String>> sendOtpSms(@RequestBody SmsOtpRequestDto smsOtpRequestDto) {
        String responseData = smsService.sendOtpSms(smsOtpRequestDto);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message(responseData)
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/otp/email")
    public ResponseEntity<ApiResponse<String>> sendOtpMail(@RequestBody EmailOtpRequestDto emailOtpRequestDto) {
        String responseData = mailService.sendOtpEmail(emailOtpRequestDto);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message(responseData)
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/verify/otp/")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpDto verifyOtpDto){
        boolean responseData = smsService.verifyOtp(verifyOtpDto);
        ApiResponse<Boolean> apiResponse = ApiResponse.<Boolean>builder()
                .data(responseData)
                .message("Otp Verified.")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

//    @DeleteMapping("/otp/{otpNumber}")
//    public ResponseEntity<?> deleteOtp(@PathVariable("id")String id){
//        Otp responseData = otpRepository.findById(id).orElse(null);
//        if (responseData != null){
//            otpRepository.delete(responseData);
//        }
//        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
//                .data("Otp Deleted")
//                .message("Deleted")
//                .timestamp(LocalDateTime.now())
//                .statusCode(HttpStatus.OK.value())
//                .success(true)
//                .build();
//
//        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
//    }



}
