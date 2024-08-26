package com.urbanchic.repository;

import com.urbanchic.entity.SellerDocument;
import com.urbanchic.entity.sellerEnum.SellerStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerDocumentRepository extends MongoRepository<SellerDocument, String> {
    List<SellerDocument> findBySellerStatus(SellerStatus sellerStatus);

    SellerDocument findBySellerId(String sellerId);
}
