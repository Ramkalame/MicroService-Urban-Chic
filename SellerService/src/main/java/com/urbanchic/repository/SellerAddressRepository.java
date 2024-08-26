package com.urbanchic.repository;

import com.urbanchic.entity.SellerAddress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerAddressRepository extends MongoRepository<SellerAddress,String> {

    Optional<SellerAddress> findBySellerId(String sellerId);
}
