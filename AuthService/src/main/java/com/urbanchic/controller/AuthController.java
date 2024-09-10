package com.urbanchic.controller;

import com.urbanchic.dto.LoginResponseDto;
import com.urbanchic.dto.seller.SellerLoginRequestDto;
import com.urbanchic.dto.seller.SellerRegistrationDto;
import com.urbanchic.entity.User;
import com.urbanchic.service.AuthService;
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

    @PostMapping("/register/seller")
    public ResponseEntity<?> createSellerUser(@RequestBody @Valid SellerRegistrationDto sellerRegistrationDto){
        User responseData = authService.createSellerUser(sellerRegistrationDto);

        ApiResponse<User> apiResponse = ApiResponse.<User>builder()
                .data(responseData)
                .message("User is registered successfully.")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/login/seller")
    public ResponseEntity<?> sellerLogin(@RequestBody @Valid SellerLoginRequestDto sellerLoginRequestDto){
        LoginResponseDto responseData = authService.sellerLogin(sellerLoginRequestDto);

        ApiResponse<LoginResponseDto> apiResponse = ApiResponse.<LoginResponseDto>builder()
                .data(responseData)
                .message("Logged In Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/account-status/{sellerId}")
    public ResponseEntity<?> updateSellerAccountStatus(@PathVariable("sellerId") String sellerId){
        User responseData = authService.updateSellerAccountStatus(sellerId);

        ApiResponse<User> apiResponse = ApiResponse.<User>builder()
                .data(responseData)
                .message("Account Status is updated")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }




    @GetMapping("/dummy")
    public String get(){
        return "Hello";
    }




}
