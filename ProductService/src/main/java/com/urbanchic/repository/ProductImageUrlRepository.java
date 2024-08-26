package com.urbanchic.repository;

import com.urbanchic.entity.ProductImageUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageUrlRepository extends MongoRepository<ProductImageUrl,String> {
    List<ProductImageUrl> findAllProductImageUrlByProductId(String productId);
}
