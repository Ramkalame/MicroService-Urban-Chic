package com.urbanchic.repository;

import com.urbanchic.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

    Page<Product> findProductBySellerId(String sellerId, Pageable pageable);
    @Query("{ 'productCategory':{$regex: ?0, $options:'i' }}")
    List<Product> findProductsByProductCategory(String productCategory);
    @Query("{ 'productSubCategory':{$regex: ?0, $options:'i' }}")
    List<Product> findProductsByProductSubCategory(String productSubCategory);
    @Query("{ 'productType':{$regex: ?0, $options:'i' }}")
    List<Product> findProductsByProductType(String productType);

    //other methods
    Optional<Product> findByProductIdAndSellerId(String productId,String sellerId);
    int countBySellerId(String sellerId);

}
