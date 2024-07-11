package com.urbanchic.repository;

import com.urbanchic.entity.ReviewImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewImageRepository extends MongoRepository<ReviewImage,String> {
    List<ReviewImage> findAllByReviewId(String reviewId);
}
