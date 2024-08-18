package com.urbanchic.controller;

import com.urbanchic.model.Company;
import com.urbanchic.model.Seller;
import com.urbanchic.model.SellerDocument;
import com.urbanchic.modelDTO.CompanyDto;
import com.urbanchic.modelDTO.SellerDocumentDto;
import com.urbanchic.modelDTO.SellerDto;
import com.urbanchic.service.CompanyService;
import com.urbanchic.service.SellerDocumentService;
import com.urbanchic.service.SellerService;
import com.urbanchic.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {


     private final SellerService sellerService;

     private final CompanyService companyService;

     private final SellerDocumentService sellerDocumentService;

    @PostMapping("/create")
    public ResponseEntity<?> createSeller(@Valid @RequestBody SellerDto sellerDto) {
        Seller response = sellerService.createSeller(sellerDto);
        ApiResponse<Seller> apiResponse = ApiResponse.<Seller>builder()
                .data(response)
                .message("user Created successfully !")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/add-document")
    public ResponseEntity<?> addSellerDocument(@Valid @RequestBody SellerDocumentDto sellerDocumentDto) {
        SellerDocument createdDocument = sellerDocumentService.addSellerDocument(sellerDocumentDto);
        ApiResponse<SellerDocument> apiResponse = ApiResponse.<SellerDocument>builder()
                .data(createdDocument)
                .message("Seller document created successfully!")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/add-company")
    public ResponseEntity<?> addCompany(@Valid @RequestBody CompanyDto companyDto) {
        Company createdCompany = companyService.addCompany(companyDto);
        ApiResponse<Company> apiResponse = ApiResponse.<Company>builder()
                .data(createdCompany)
                .message("Company created successfully!")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("/pending-seller")
    public ResponseEntity<List<SellerDocument>> getListOfPendingSellers() {
        List<SellerDocument> response = sellerService.getListOfPendingSeller();
        ApiResponse<List<SellerDocument>> apiResponse = ApiResponse.<List<SellerDocument>>builder()
                .data(response)
                .message("Here is a list of pending seller")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/seller-by-email")
    public ResponseEntity<ApiResponse<Seller>> getSellerBySellerId(@RequestParam String email) {
        Seller response = sellerService.getSellerBySellerId(email);
        ApiResponse<Seller> apiResponse = ApiResponse.<Seller>builder()
                .data(response)
                .message("Seller found with email: " + email)
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/approve/{sellerId}")
    public ResponseEntity<ApiResponse<Boolean>> approveVerificationRequest(@PathVariable String sellerId) {
        boolean response = sellerDocumentService.approveVerificationRequest(sellerId);
        ApiResponse<Boolean> apiResponse = ApiResponse.<Boolean>builder()
                .data(response)
                .message("Seller verification approved for ID: " + sellerId)
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    // also mail sent to this user
    @PutMapping("/reject/{sellerId}")
    public ResponseEntity<ApiResponse<Boolean>> rejectVerificationRequest(@PathVariable String sellerId) {
        boolean response = sellerDocumentService.rejectVerificationRequest(sellerId);
        ApiResponse<Boolean> apiResponse = ApiResponse.<Boolean>builder()
                .data(response)
                .message("Seller verification rejected for ID: " + sellerId)
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
