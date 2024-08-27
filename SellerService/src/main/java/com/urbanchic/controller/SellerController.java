package com.urbanchic.controller;

import com.urbanchic.entity.Seller;
import com.urbanchic.dto.SellerDto;
import com.urbanchic.service.SellerService;
import com.urbanchic.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class SellerController {

     private final SellerService sellerService;

    @PostMapping("/create")
    public ResponseEntity<?> createSeller(@Valid @RequestBody SellerDto sellerDto) {
        Seller responseData = sellerService.createSeller(sellerDto);

        ApiResponse<Seller> apiResponse = ApiResponse.<Seller>builder()
                .data(responseData)
                .message("user Created successfully !")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/update/{sellerId}")
    public  ResponseEntity<?> updateSeller(@PathVariable("sellerId") String sellerId,@RequestBody SellerDto sellerDto){
        Seller responseData = sellerService.updateSeller(sellerId,sellerDto);

        ApiResponse<Seller> apiResponse = ApiResponse.<Seller>builder()
                .data(responseData)
                .message("seller updated successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<ApiResponse<Seller>> getSellerBySellerId(@PathVariable("sellerId") String sellerId) {
        Seller responseData = sellerService.getSellerBySellerId(sellerId);
        ApiResponse<Seller> apiResponse = ApiResponse.<Seller>builder()
                .data(responseData)
                .message("Seller Details Fetched Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/account-status/{accountStatus}")
    public ResponseEntity<?> getAllSellerByAccountStatus(@PathVariable("accountStatus") String accountStatus){
        List<Seller> responseData = sellerService.getAllSellerByAccountStatus(accountStatus);

        ApiResponse<List<Seller>> apiResponse = ApiResponse.<List<Seller>>builder()
                .data(responseData)
                .message("Seller List With Account Status Fetched Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<Seller>> getSellerBySellerPrimaryEmail(@PathVariable("email") String email) {
        Seller responseData = sellerService.getSellerByPrimaryEmail(email);
        ApiResponse<Seller> apiResponse = ApiResponse.<Seller>builder()
                .data(responseData)
                .message("Seller Details Fetched Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/seller/{sellerId}")
    public ResponseEntity<?> deleteSeller(@PathVariable("sellerId") String sellerId){
        String responseData = sellerService.deleteSeller(sellerId);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message("Seller Details Deleted Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
