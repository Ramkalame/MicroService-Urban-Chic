package com.urbanchic.repository;

import com.urbanchic.model.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends MongoRepository<Seller, String> {
    Seller findBySellerPrimaryEmail(String email);
}
