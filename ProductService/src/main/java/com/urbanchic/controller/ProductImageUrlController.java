package com.urbanchic.controller;

import com.urbanchic.dto.ProductImageUrlDto;
import com.urbanchic.entity.ProductImageUrl;
import com.urbanchic.service.ProductImageUrlService;
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
@RequestMapping("/api/v1/product-image-urls")
public class ProductImageUrlController {

    private final ProductImageUrlService productImageUrlService;

    @PostMapping("/add")
    public ResponseEntity<?> addProductImageUrl(@RequestBody ProductImageUrlDto productImageUrlDto){
        ProductImageUrl responseData = productImageUrlService.addProductImageUrl(productImageUrlDto);

        ApiResponse<ProductImageUrl> apiResponse = ApiResponse.<ProductImageUrl>builder()
                .data(responseData)
                .message("Image Added Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getAllProductImageUrlByProductId(@PathVariable("productId") String productId){
        List<ProductImageUrl> responseData = productImageUrlService.getAllProductImageUrlByProductId(productId);

        ApiResponse<List<ProductImageUrl>> apiResponse = ApiResponse.<List<ProductImageUrl>>builder()
                .data(responseData)
                .message("All Images Fetched Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductImageUrlById(@PathVariable("id") String id){
        String responseData = productImageUrlService.deleteProductImageUrlById(id);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message("Image Deleted Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductImageUrlById(@PathVariable("id") String id) {
        ProductImageUrl responseData = productImageUrlService.getProductImageUrlById(id);

        ApiResponse<ProductImageUrl> apiResponse = ApiResponse.<ProductImageUrl>builder()
                .data(responseData)
                .message("Image Fetched Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
