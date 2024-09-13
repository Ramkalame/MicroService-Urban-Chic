package com.urbanchic.controller;

import com.urbanchic.dto.ProductImageDto;
import com.urbanchic.service.CloudinaryImageService;
import com.urbanchic.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products/seller/image")
@RequiredArgsConstructor
public class CloudinaryImageController {

    private final CloudinaryImageService cloudinaryImageService;

    @PostMapping("/upload/{productId}")
    public ResponseEntity<?> uploadImage(@PathVariable("productId") String productId,
                                         @RequestParam("files") List<MultipartFile> files){
        List<ProductImageDto> responseData = cloudinaryImageService.uploadIImage(productId,files);

        ApiResponse<List<ProductImageDto>> apiResponse = ApiResponse.<List<ProductImageDto>>builder()
                .data(responseData)
                .message("Image Uploaded Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
