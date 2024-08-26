package com.urbanchic.controller;

import com.urbanchic.dto.SellerAddressDto;
import com.urbanchic.entity.SellerAddress;
import com.urbanchic.service.SellerAddressService;
import com.urbanchic.utils.ApiResponse;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seller-address")
public class SellerAddressController {

    private final SellerAddressService sellerAddressService;

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<?> getSellerAddressBySellerId(@PathVariable("sellerId") String sellerId){
        SellerAddress responseData = sellerAddressService.getSellerAddressBySellerId(sellerId);

        ApiResponse<SellerAddress> apiResponse = ApiResponse.<SellerAddress>builder()
                .data(responseData)
                .message("Address is Fetched successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSellerAddress(@PathVariable("id") String id,
                                                 @RequestBody SellerAddressDto sellerAddressDto){
        SellerAddress responseData = sellerAddressService.updateSellerAddress(id,sellerAddressDto);

        ApiResponse<SellerAddress> apiResponse = ApiResponse.<SellerAddress>builder()
                .data(responseData)
                .message("Address is updated successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
