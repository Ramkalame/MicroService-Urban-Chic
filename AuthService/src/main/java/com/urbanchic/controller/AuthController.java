package com.urbanchic.controller;

import com.urbanchic.dto.*;
import com.urbanchic.entity.Otp;
import com.urbanchic.external.User;
import com.urbanchic.service.AuthService;
import com.urbanchic.service.OtpService;
import com.urbanchic.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final OtpService otpService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> buyerRegister(@RequestBody UserRegistrationDto userRegistrationDto) {
        ApiResponse<User> responseData = authService.buyerRegister(userRegistrationDto);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/register/social")
    public ResponseEntity<?> buyerSocialRegister(@RequestBody UserSocialRegistrationDto userSocialRegistrationDto) {
        ApiResponse<User> responseData = authService.buyerSocialRegister(userSocialRegistrationDto);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/login")
    public ResponseEntity<?> normalLogin(@RequestBody LoginRequestDto loginRequestDto) {
        String responseData = authService.login(loginRequestDto);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message("Logged in successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/send/otp")
    public ResponseEntity<?> sendOtp(@RequestBody OtpRequestDto otpRequestDto) {
        Otp responseData = otpService.sendOtp(otpRequestDto);
        ApiResponse<Otp> apiResponse = ApiResponse.<Otp>builder()
                .data(responseData)
                .message("Otp Sent to mobile number : " + responseData.getMoNumber() + " successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/verify/otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerificationDto otpVerificationDto) {
        boolean responseData = otpService.verifyOtp(otpVerificationDto);
        ApiResponse<Boolean> apiResponse = ApiResponse.<Boolean>builder()
                .data(responseData)
                .message(responseData)
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/dummy")
    public String get(){
        return "Hello";
    }




}
