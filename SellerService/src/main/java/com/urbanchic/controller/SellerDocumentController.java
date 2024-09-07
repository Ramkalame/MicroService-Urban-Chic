package com.urbanchic.controller;

import com.urbanchic.dto.SellerDocumentDto;
import com.urbanchic.entity.SellerDocument;
import com.urbanchic.service.SellerDocumentService;
import com.urbanchic.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sellers/documents")
@CrossOrigin("http://localhost:4200")
public class SellerDocumentController {

    private final SellerDocumentService sellerDocumentService;

    @PostMapping("/add")
    public ResponseEntity<?> addSellerDocument(@RequestBody @Valid SellerDocumentDto sellerDocumentDto){
        SellerDocument responseData = sellerDocumentService.addSellerDocument(sellerDocumentDto);

        ApiResponse<SellerDocument> apiResponse = ApiResponse.<SellerDocument>builder()
                .data(responseData)
                .message("Seller Document Added Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSellerDocument(@PathVariable("id") String id,SellerDocumentDto sellerDocumentDto){
        SellerDocument responseData = sellerDocumentService.updateSellerDocument(id,sellerDocumentDto);

        ApiResponse<SellerDocument> apiResponse = ApiResponse.<SellerDocument>builder()
                .data(responseData)
                .message("Seller Document Updated Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<?> getSellerDocumentBySellerId(@PathVariable("sellerId") String sellerId){
        SellerDocument responseData = sellerDocumentService.getSellerDocumentBySellerId(sellerId);

        ApiResponse<SellerDocument> apiResponse = ApiResponse.<SellerDocument>builder()
                .data(responseData)
                .message("Seller Document Fetched Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
