package com.urbanchic.repository;

import com.urbanchic.entity.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends MongoRepository<Seller, String> {
    List<Seller> findBySellerAccountStatus(String sellerAccountStatus);
    Optional<Seller> findBySellerPrimaryEmail(String primaryEmail);
    Optional<Seller> findBySellerId(String sellerId);
}
