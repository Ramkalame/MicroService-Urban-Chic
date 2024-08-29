package com.urbanchic.controller;

import com.urbanchic.dto.*;
import com.urbanchic.entity.Otp;
import com.urbanchic.entity.User;
import com.urbanchic.external.Seller;
import com.urbanchic.external.SellerDto;
import com.urbanchic.service.AuthService;
import com.urbanchic.service.OtpService;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final OtpService otpService;

    @PostMapping("/register/seller")
    public ResponseEntity<?> createSellerUser(@RequestBody @Valid SellerRegistrationDto sellerRegistrationDto){
        User responseData = authService.createSellerUser(sellerRegistrationDto);

        ApiResponse<User> apiResponse = ApiResponse.<User>builder()
                .data(responseData)
                .message("User is created.")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }




    @GetMapping("/dummy")
    public String get(){
        return "Hello";
    }




}
