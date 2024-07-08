package com.urbanchic.repository;

import com.urbanchic.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

    List<Product> findProductBySellerId(String sellerId);


}
