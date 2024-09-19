package com.urbanchic.service.impl;

import com.urbanchic.dto.ProductDto;
import com.urbanchic.dto.ReviewDto;
import com.urbanchic.entity.Product;
import com.urbanchic.entity.Review;
import com.urbanchic.event.DeleteAllReviewImagesOfReviewEvent;
import com.urbanchic.event.DeleteAllReviewsOfProductEvent;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.ProductRepository;
import com.urbanchic.repository.ReviewRepository;
import com.urbanchic.service.ProductService;
import com.urbanchic.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ApplicationEventPublisher eventPublisher;
    private final ReviewRepository reviewRepository;
    private final ProductService productService;


    @Override
    public Review addReview(ReviewDto addReviewDto) {
        Review newReview = Review.builder()
                .reviewDescription(addReviewDto.getReviewDescription())
                .rating(addReviewDto.getRating())
                .productId(addReviewDto.getProductId())
                .userId(addReviewDto.getUserId())
                .build();
        Review savedReview = reviewRepository.save(newReview);
        Product existingProduct = productService.getProductByProductId(addReviewDto.getProductId());
        switch (savedReview.getRating()){
            case 1:existingProduct.setOneStarCount(existingProduct.getOneStarCount()+1); break;
            case 2:existingProduct.setTwoStarCount(existingProduct.getTwoStarCount()+1); break;
            case 3:existingProduct.setThreeStarCount(existingProduct.getThreeStarCount()+1); break;
            case 4:existingProduct.setFourStarCount(existingProduct.getFourStarCount()+1); break;
            case 5:existingProduct.setFiveStarCount(existingProduct.getFiveStarCount()+1); break;
        }
        ProductDto updatedProductDto = ProductDto.builder()
                .productName(existingProduct.getProductName())
//                .productPrice(existingProduct.getProductPrice())
//                .productDescription(existingProduct.getProductDescription())
//                .productImageUrl(existingProduct.getProductImageUrl())
//                .productQuantity(existingProduct.getProductQuantity())
//                .productBrand(existingProduct.getProductBrand())
//                .productCategory(existingProduct.getProductCategory())
//                .productSubCategory(existingProduct.getProductSubCategory())
//                .productType(existingProduct.getProductType())
//                .attributes(existingProduct.getAttributes())
//                .variants(existingProduct.getVariants())
//                .oneStarCount(existingProduct.getOneStarCount())
//                .twoStarCount(existingProduct.getTwoStarCount())
//                .threeStarCount(existingProduct.getThreeStarCount())
//                .fourStarCount(existingProduct.getFourStarCount())
//                .fiveStarCount(existingProduct.getFiveStarCount())
                .build();
        productService.updateProductByProductId(savedReview.getProductId(),updatedProductDto);
        return  savedReview;
    }

    @Override
    public Review updateReview(ReviewDto updateReviewDto,String reviewId) {
        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(()->
                new EntityNotFoundException("Review Does Not Exists."));
        Product existingProduct = productService.getProductByProductId(updateReviewDto.getProductId());
        switch (existingReview.getRating()){
            case 1:existingProduct.setOneStarCount(existingProduct.getOneStarCount()-1); break;
            case 2:existingProduct.setTwoStarCount(existingProduct.getTwoStarCount()-1); break;
            case 3:existingProduct.setThreeStarCount(existingProduct.getThreeStarCount()-1); break;
            case 4:existingProduct.setFourStarCount(existingProduct.getFourStarCount()-1); break;
            case 5:existingProduct.setFiveStarCount(existingProduct.getFiveStarCount()-1); break;
        }
        existingReview.setReviewDescription(updateReviewDto.getReviewDescription());
        existingReview.setRating(updateReviewDto.getRating());
        existingReview.setProductId(updateReviewDto.getProductId());
        existingReview.setUserId(updateReviewDto.getUserId());
        Review updatedReview = reviewRepository.save(existingReview);

        switch (updateReviewDto.getRating()){
            case 1:existingProduct.setOneStarCount(existingProduct.getOneStarCount()+1); break;
            case 2:existingProduct.setTwoStarCount(existingProduct.getTwoStarCount()+1); break;
            case 3:existingProduct.setThreeStarCount(existingProduct.getThreeStarCount()+1); break;
            case 4:existingProduct.setFourStarCount(existingProduct.getFourStarCount()+1); break;
            case 5:existingProduct.setFiveStarCount(existingProduct.getFiveStarCount()+1); break;
        }
        ProductDto updatedProductDto = ProductDto.builder()
//                .productName(existingProduct.getProductName())
//                .productPrice(existingProduct.getProductPrice())
//                .productDescription(existingProduct.getProductDescription())
//                .productImageUrl(existingProduct.getProductImageUrl())
//                .productQuantity(existingProduct.getProductQuantity())
//                .productBrand(existingProduct.getProductBrand())
//                .productCategory(existingProduct.getProductCategory())
//                .productSubCategory(existingProduct.getProductSubCategory())
//                .productType(existingProduct.getProductType())
//                .attributes(existingProduct.getAttributes())
//                .variants(existingProduct.getVariants())
//                .oneStarCount(existingProduct.getOneStarCount())
//                .twoStarCount(existingProduct.getTwoStarCount())
//                .threeStarCount(existingProduct.getThreeStarCount())
//                .fourStarCount(existingProduct.getFourStarCount())
//                .fiveStarCount(existingProduct.getFiveStarCount())
                .build();
        productService.updateProductByProductId(updateReviewDto.getProductId(),updatedProductDto);
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

    @Override
    public String deleteReviewById(String reviewId) {
        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(() ->
                new EntityNotFoundException("Review Not Found"));
        reviewRepository.delete(existingReview);
        return "Review Deleted";
    }

    @Override
    public void deleteAllReviewByProductId(String productId) {
        List<Review> reviewList = reviewRepository.findAllByProductId(productId);
        if (!reviewList.isEmpty()){
            reviewRepository.deleteAll(reviewList);
        }
    }




}
