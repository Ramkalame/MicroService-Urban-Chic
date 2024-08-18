package com.urbanchic.service.impl;

import com.urbanchic.dto.ReviewImageDto;
import com.urbanchic.entity.ReviewImage;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.ReviewImageRepository;
import com.urbanchic.service.ReviewImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewImageServiceImpl implements ReviewImageService {

    private final ReviewImageRepository reviewImageRepository;


    @Override
    public ReviewImage addReviewImage(ReviewImageDto addReviewImageDto) {

        ReviewImage newReviewImage = ReviewImage.builder()
                .reviewId(addReviewImageDto.getReviewId())
                .imageUrl(addReviewImageDto.getImageUrl())
                .build();
        return reviewImageRepository.save(newReviewImage);
    }

    @Override
    public String removeReviewImage(String reviewImageId) {
        ReviewImage existingReviewImage = reviewImageRepository.findById(reviewImageId).orElseThrow(()->
                new EntityNotFoundException("Review Image Does Not Exists."));
        reviewImageRepository.delete(existingReviewImage);
        return "Image is removed";
    }

    @Override
    public List<ReviewImage> getAllReviewImageOfReview(String reviewId) {
        List<ReviewImage> reviewImageList = reviewImageRepository.findAllByReviewId(reviewId);
        if (reviewImageList.isEmpty()){
            throw new EntityNotFoundException("No Images for the Requested Review");
        }
        return reviewImageList;
    }
}
