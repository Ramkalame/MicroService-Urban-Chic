package com.urbanchic.controller;

import com.urbanchic.dto.ImageUploadResponseDto;
import com.urbanchic.service.CloudinaryImageService;
import com.urbanchic.utils.ApiResponse;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sellers/images")
@CrossOrigin("http://localhost:4200")
public class CloudinaryImageController {

    private final CloudinaryImageService cloudinaryImageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile multipartFile){
        ImageUploadResponseDto responseData = cloudinaryImageService.uploadIImage(multipartFile);

        ApiResponse<ImageUploadResponseDto> apiResponse = ApiResponse.<ImageUploadResponseDto>builder()
                .data(responseData)
                .message("Image Uploaded Successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteImage(@QueryParam("publicId") String publicId){
        String responseData = cloudinaryImageService.deleteImage(publicId);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message(responseData)
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
