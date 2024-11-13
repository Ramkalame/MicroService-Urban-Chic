package com.urbanchic.controller;

import com.urbanchic.dto.AddressDto;
import com.urbanchic.dto.BuyerDto;
import com.urbanchic.entity.Buyer;
import com.urbanchic.entity.helper.Address;
import com.urbanchic.service.BuyerService;
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
@RequestMapping("/api/v1/buyers")
@CrossOrigin(origins = "http://localhost:4200")
public class BuyerController {

    private final BuyerService buyerService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<?>> createBuyer(@RequestBody @Valid BuyerDto buyerDto) {
        Buyer responseData = buyerService.createBuyer(buyerDto);
        ApiResponse<Buyer> apiResponse = ApiResponse.<Buyer>builder()
                .data(responseData)
                .message("User with email  " + responseData.getEmail() + "  registered successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/buyerId/{buyerId}")
    public ResponseEntity<ApiResponse<?>> getBuyerByBuyerId(@PathVariable("buyerId") String buyerId){
        Buyer responseData = buyerService.getBuyerByBuyerId(buyerId);
        ApiResponse<Buyer> apiResponse = ApiResponse.<Buyer>builder()
                .data(responseData)
                .message("User Fetched Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/phone-number/{phoneNumber}")
    public ResponseEntity<ApiResponse<?>> getBuyerByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        Buyer responseData = buyerService.getBuyerByPhoneNumber(phoneNumber);

        ApiResponse<Buyer> apiResponse = ApiResponse.<Buyer>builder()
                .data(responseData)
                .message("User Fetched Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<?>> getBuyerByEmail(@PathVariable("email") String email){
        Buyer responseData = buyerService.getBuyerByEmail(email);

        ApiResponse<Buyer> apiResponse = ApiResponse.<Buyer>builder()
                .data(responseData)
                .message("User Fetched Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }



    @PutMapping("/update-name/{name}/{buyerId}")
    public ResponseEntity<ApiResponse<?>> updateBuyerName(@PathVariable("name") String name,
                                                             @PathVariable("buyerId") String buyerId){
        Buyer responseData = buyerService.updateBuyerName(name,buyerId);

        ApiResponse<Buyer> apiResponse = ApiResponse.<Buyer>builder()
                .data(responseData)
                .message("User Details Updated Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update-gender/{gender}/{buyerId}")
    public ResponseEntity<ApiResponse<?>> updateBuyerGender(@PathVariable("gender") String gender,
                                                          @PathVariable("buyerId") String buyerId){
        Buyer responseData = buyerService.updateBuyerGender(gender,buyerId);

        ApiResponse<Buyer> apiResponse = ApiResponse.<Buyer>builder()
                .data(responseData)
                .message("User Details Updated Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update-email/{email}/{buyerId}")
    public ResponseEntity<ApiResponse<?>> updateBuyerEmail(@PathVariable("email") String email,
                                                          @PathVariable("buyerId") String buyerId){
        Buyer responseData = buyerService.updateBuyerEmail(email,buyerId);

        ApiResponse<Buyer> apiResponse = ApiResponse.<Buyer>builder()
                .data(responseData)
                .message("User Details Updated Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update-phoneNumber/{phoneNumber}/{buyerId}")
    public ResponseEntity<ApiResponse<?>> updateBuyerPhoneNumber(@PathVariable("phoneNumber") String phoneNumber,
                                                          @PathVariable("buyerId") String buyerId){
        Buyer responseData = buyerService.updateBuyerPhoneNumber(phoneNumber,buyerId);

        ApiResponse<Buyer> apiResponse = ApiResponse.<Buyer>builder()
                .data(responseData)
                .message("User Details Updated Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{buyerId}")
    public ResponseEntity<ApiResponse<?>> deleteBuyer(@PathVariable("buyerId") String buyerId){
        buyerService.deleteBuyer(buyerId);

        ApiResponse<Buyer> apiResponse = ApiResponse.<Buyer>builder()
                .data(null)
                .message("User Deleted Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/{buyerId}/addresses")
    public ResponseEntity<?> getBuyerAddresses(@PathVariable("buyerId") String buyerId){
        List<Address> responseData = buyerService.getBuyerAddresses(buyerId);

        ApiResponse<List<Address>> apiResponse = ApiResponse.<List<Address>>builder()
                .data(responseData)
                .message("Buyer addresses fetched successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return  ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/{buyerId}/address")
    public ResponseEntity<?> createAddress(@RequestBody AddressDto addressDto,
                                           @PathVariable("buyerId") String buyerId){
        Address responseData = buyerService.createAddress(addressDto,buyerId);

        ApiResponse<Address> apiResponse = ApiResponse.<Address>builder()
                .data(responseData)
                .message("address added successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/{buyerId}/address/{addressId}")
    public ResponseEntity<?> updateAddress(@RequestBody AddressDto addressDto,
                                           @PathVariable("buyerId") String buyerId,
                                           @PathVariable("addressId") String addressId){
        List<Address> responseData =  buyerService.updateAddress(addressDto,buyerId,addressId);

        ApiResponse<List<Address> > apiResponse = ApiResponse.<List<Address> >builder()
                .data(responseData)
                .message("address updated successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/{buyerId}/address/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("buyerId") String buyerId,
                                           @PathVariable("addressId")  String addressId){
      List<Address> responseData =  buyerService.deleteAddress(buyerId,addressId);

        ApiResponse<List<Address>> apiResponse = ApiResponse.<List<Address>>builder()
                .data(responseData)
                .message("address deleted successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }






}
