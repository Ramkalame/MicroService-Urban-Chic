package com.urbanchic.controller;

import com.urbanchic.dto.SellerAddressDto;
import com.urbanchic.entity.SellerAddress;
import com.urbanchic.service.SellerAddressService;
import com.urbanchic.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sellers/address")
public class SellerAddressController {

    private final SellerAddressService sellerAddressService;


    @PostMapping("/add")
    public ResponseEntity<?> addSellerAddress(@RequestBody @Valid SellerAddressDto sellerAddressDto){
        SellerAddress responseData = sellerAddressService.addSellerAddress(sellerAddressDto);

        ApiResponse<SellerAddress> apiResponse = ApiResponse.<SellerAddress>builder()
                .data(responseData)
                .message("Seller Address is added successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<?> getSellerAddressBySellerId(@PathVariable("sellerId") String sellerId){
        SellerAddress responseData = sellerAddressService.getSellerAddressBySellerId(sellerId);

        ApiResponse<SellerAddress> apiResponse = ApiResponse.<SellerAddress>builder()
                .data(responseData)
                .message("Seller Address is Fetched successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSellerAddress(@PathVariable("id") String id,
                                                 @RequestBody SellerAddressDto sellerAddressDto){
        SellerAddress responseData = sellerAddressService.updateSellerAddress(id, sellerAddressDto);

        ApiResponse<SellerAddress> apiResponse = ApiResponse.<SellerAddress>builder()
                .data(responseData)
                .message("Seller Address is updated successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
