package com.urbanchic.service;

import com.urbanchic.dto.ReviewImageDto;
import com.urbanchic.entity.ReviewImage;
import com.urbanchic.event.DeleteAllReviewImagesOfReviewEvent;

import java.util.List;

public interface ReviewImageService {

    ReviewImage addReviewImage(ReviewImageDto addReviewImageDto);

    String removeReviewImage(String reviewImageId);

    List<ReviewImage> getAllReviewImageOfReview(String reviewId);

    void deleteAllReviewImagesOfReview(DeleteAllReviewImagesOfReviewEvent deleteAllReviewImagesOfReviewEvent);
}
