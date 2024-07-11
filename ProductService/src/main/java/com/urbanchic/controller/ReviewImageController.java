package com.urbanchic.controller;

import com.urbanchic.dto.ReviewImageDto;
import com.urbanchic.entity.ReviewImage;
import com.urbanchic.service.ReviewImageService;
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
@RequestMapping("/reviewimage")
public class ReviewImageController {

    private final ReviewImageService reviewImageService;

    @PostMapping("/add")
    public ResponseEntity<?> addReviewImage(@RequestBody @Valid ReviewImageDto reviewImageDto){
        ReviewImage responseData = reviewImageService.addReviewImage(reviewImageDto);

        ApiResponse<ReviewImage> apiResponse = ApiResponse.<ReviewImage>builder()
                .data(responseData)
                .message("Review Image is added successfully")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/delete/{reviewImageId}")
    public ResponseEntity<?> removeReviewImage(@PathVariable("reviewImageId") String reviewImageId){
        String responseData = reviewImageService.removeReviewImage(reviewImageId);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message("Review Image is deleted successfully")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getAllReviewImageOfReview(@PathVariable("reviewId") String reviewId){
        List<ReviewImage> responseData = reviewImageService.getAllReviewImageOfReview(reviewId);

        ApiResponse<List<ReviewImage>> apiResponse = ApiResponse.<List<ReviewImage>>builder()
                .data(responseData)
                .message("Review Image Fetched successfully")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
