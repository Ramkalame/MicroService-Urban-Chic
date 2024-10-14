package com.urbanchic.repository;

import com.urbanchic.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart,String> {
    Optional<Cart> findByBuyerId(String buyerId);
}
