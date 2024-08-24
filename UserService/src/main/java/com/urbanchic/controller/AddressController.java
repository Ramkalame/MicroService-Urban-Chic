package com.urbanchic.controller;

import com.urbanchic.entity.Address;
import com.urbanchic.service.AddressService;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private  final AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody @Valid Address address){
        Address responseData = addressService.createAddress(address);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(responseData)
                .message("Address Created")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllAddressByUserId(@PathVariable String userId){
       List<Address> responseData =  addressService.getAllAddressByUserId(userId);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(responseData)
                .message("Address Created")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update")
    public  ResponseEntity<?> updateAddress(@RequestBody @Valid Address address){
        Address responseData = addressService.updateAddress(address);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(responseData)
                .message("Address Updated")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/delete/{addressId}")
    public  ResponseEntity<?> deleteAddress(@PathVariable String addressId){
        String responseData = addressService.deleteAddress(addressId);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(responseData)
                .message("Address Deleted")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);

    }



}
