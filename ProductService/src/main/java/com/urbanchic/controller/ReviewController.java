package com.urbanchic.controller;

import com.urbanchic.dto.ReviewDto;
import com.urbanchic.entity.Product;
import com.urbanchic.entity.Review;
import com.urbanchic.service.ReviewService;
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
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody @Valid ReviewDto addReviewDto){
        Review responseData = reviewService.addReview(addReviewDto);

        ApiResponse<Review> apiResponse = ApiResponse.<Review>builder()
                .data(responseData)
                .message("Review is added successfully")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/update/{reviewId}")
    public ResponseEntity<?> updateReview(@RequestBody @Valid ReviewDto updateReviewDto,@PathVariable("reviewId") String reviewId){
        Review responseData = reviewService.updateReview(updateReviewDto,reviewId);

        ApiResponse<Review> apiResponse = ApiResponse.<Review>builder()
                .data(responseData)
                .message("Review is updated successfully")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getAllReviewOfProduct(@PathVariable("productId") String productId){
        List<Review> responseData = reviewService.getAllReviewOfProduct(productId);

        ApiResponse<List<Review>> apiResponse = ApiResponse.<List<Review>>builder()
                .data(responseData)
                .message("All Review is Fetched Successfully")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{userId}/{productId}")
    public ResponseEntity<?> getReviewOfProductByUser(@PathVariable("userId") String userId,
                                                      @PathVariable("productId") String productId){
        Review responseData = reviewService.getReviewOfProductByUser(userId,productId);

        ApiResponse<Review> apiResponse = ApiResponse.<Review>builder()
                .data(responseData)
                .message("Review is Fetched successfully")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
