package com.urbanchic.repository;

import com.urbanchic.entity.Review;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review,String> {
    List<Review> findAllByProductId(String productId);
    Optional<Review> findByProductIdAndUserId(String productId, @NotBlank(message = "User Id is mandatory") String userId);
}
