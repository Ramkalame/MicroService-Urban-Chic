package com.urbanchic.repository;

import com.urbanchic.entity.SellerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerDocumentRepository extends MongoRepository<SellerDocument,String> {
    Optional<SellerDocument> findBySellerId(String sellerId);
    Optional<SellerDocument> findByGstNumber(String gstNumber);
}
