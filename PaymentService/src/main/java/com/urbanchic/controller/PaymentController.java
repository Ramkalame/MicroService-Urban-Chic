package com.urbanchic.controller;

import com.urbanchic.dto.PaymentDto;
import com.urbanchic.dto.PaymentOrderResponseDto;
import com.urbanchic.entity.Payment;
import com.urbanchic.service.PaymentService;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
@CrossOrigin("http://localhost:4200")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/createorder/{amount}")
    public ResponseEntity<?> createOrder(@PathVariable("amount") double amount){
        PaymentOrderResponseDto responseData = paymentService.createOrder(amount);

        ApiResponse<PaymentOrderResponseDto> apiResponse = ApiResponse.<PaymentOrderResponseDto>builder()
                .data(responseData)
                .message("Payment Order Created")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPaymentDetails(@RequestBody @Valid PaymentDto paymentDto){
        Payment responseData = paymentService.addPaymentDetails(paymentDto);

        ApiResponse<Payment> apiResponse = ApiResponse.<Payment>builder()
                .data(responseData)
                .message("Payment Details Added")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
