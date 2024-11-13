package com.urbanchic.repository;

import com.urbanchic.entity.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BuyerRepository extends MongoRepository<Buyer,String> {

    Optional<Buyer> findByBuyerId(String buyerId);
    Optional<Buyer> findByPhoneNumber(String phoneNumber);
    Optional<Buyer> findByEmail(String email);
}
