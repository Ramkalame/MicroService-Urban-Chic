package com.urbanchic.service.impl;

import com.urbanchic.dto.ReviewDto;
import com.urbanchic.entity.Review;
import com.urbanchic.entity.ReviewImage;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.ReviewRepository;
import com.urbanchic.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;


    @Override
    public Review addReview(ReviewDto addReviewDto) {
        Review newReview = Review.builder()
                .reviewDescription(addReviewDto.getReviewDescription())
                .rating(addReviewDto.getRating())
                .productId(addReviewDto.getProductId())
                .userId(addReviewDto.getUserId())
                .build();
        return reviewRepository.save(newReview);
    }

    @Override
    public Review updateReview(ReviewDto updateReviewDto,String reviewId) {
        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(()->
                new EntityNotFoundException("Review Does Not Exists."));
        existingReview.setReviewDescription(updateReviewDto.getReviewDescription());
        existingReview.setRating(updateReviewDto.getRating());
        existingReview.setProductId(updateReviewDto.getProductId());
        existingReview.setUserId(updateReviewDto.getUserId());
        return reviewRepository.save(existingReview);
    }

    @Override
    public List<Review> getAllReviewOfProduct(String productId) {
        List<Review> reviewList = reviewRepository.findAllByProductId(productId);
        if (reviewList.isEmpty()){
            throw new EntityNotFoundException("No Reviews Found For The Product");
        }
        return reviewList;
    }

    @Override
    public Review getReviewOfProductByUser(String userId, String productId) {
        Review existingReview = reviewRepository.findByProductIdAndUserId(productId,userId).orElseThrow(()->
                new EntityNotFoundException("Please Add Review"));
        return  existingReview;
    }
}
