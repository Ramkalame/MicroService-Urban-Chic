package com.urbanchic.service.impl;

import com.urbanchic.dto.ReviewDto;
import com.urbanchic.entity.Product;
import com.urbanchic.entity.Review;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.ProductRepository;
import com.urbanchic.repository.ReviewRepository;
import com.urbanchic.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;


    @Override
    public Review addReview(ReviewDto addReviewDto) {
        Review newReview = Review.builder()
                .reviewDescription(addReviewDto.getReviewDescription())
                .rating(addReviewDto.getRating())
                .productId(addReviewDto.getProductId())
                .userId(addReviewDto.getUserId())
                .build();
        Review savedReview = reviewRepository.save(newReview);

        Product existingProduct = productRepository.findById(addReviewDto.getProductId()).orElseThrow( () ->
                new EntityNotFoundException("Product Does Not Exist.")
        );
        switch (addReviewDto.getRating()){
            case 1:existingProduct.setOneStarCount(existingProduct.getOneStarCount()+1); break;
            case 2:existingProduct.setTwoStarCount(existingProduct.getTwoStarCount()+1); break;
            case 3:existingProduct.setThreeStarCount(existingProduct.getThreeStarCount()+1); break;
            case 4:existingProduct.setFourStarCount(existingProduct.getFourStarCount()+1); break;
            case 5:existingProduct.setFiveStarCount(existingProduct.getFiveStarCount()+1); break;
        }
        productRepository.save(existingProduct);
        return  savedReview;
    }

    @Override
    public Review updateReview(ReviewDto updateReviewDto,String reviewId) {
        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(()->
                new EntityNotFoundException("Review Does Not Exists."));
        existingReview.setReviewDescription(updateReviewDto.getReviewDescription());
        existingReview.setRating(updateReviewDto.getRating());
        existingReview.setProductId(updateReviewDto.getProductId());
        existingReview.setUserId(updateReviewDto.getUserId());
        Review updatedReview = reviewRepository.save(existingReview);

        Product existingProduct = productRepository.findById(updateReviewDto.getProductId()).orElseThrow( () ->
                new EntityNotFoundException("Product Does Not Exist.")
        );
        switch (existingReview.getRating()){
            case 1:existingProduct.setOneStarCount(existingProduct.getOneStarCount()-1); break;
            case 2:existingProduct.setTwoStarCount(existingProduct.getTwoStarCount()-1); break;
            case 3:existingProduct.setThreeStarCount(existingProduct.getThreeStarCount()-1); break;
            case 4:existingProduct.setFourStarCount(existingProduct.getFourStarCount()-1); break;
            case 5:existingProduct.setFiveStarCount(existingProduct.getFiveStarCount()-1); break;
        }
        switch (updateReviewDto.getRating()){
            case 1:existingProduct.setOneStarCount(existingProduct.getOneStarCount()+1); break;
            case 2:existingProduct.setTwoStarCount(existingProduct.getTwoStarCount()+1); break;
            case 3:existingProduct.setThreeStarCount(existingProduct.getThreeStarCount()+1); break;
            case 4:existingProduct.setFourStarCount(existingProduct.getFourStarCount()+1); break;
            case 5:existingProduct.setFiveStarCount(existingProduct.getFiveStarCount()+1); break;
        }
        productRepository.save(existingProduct);
        return updatedReview;
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
