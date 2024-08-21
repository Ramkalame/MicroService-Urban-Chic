package com.urbanchic.repository;

import com.urbanchic.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

    List<Product> findProductBySellerId(String sellerId);
    @Query("{ 'productCategory':{$regex: ?0, $options:'i' }}")
    List<Product> findProductsByProductCategory(String productCategory);
    @Query("{ 'productSubCategory':{$regex: ?0, $options:'i' }}")
    List<Product> findProductsByProductSubCategory(String productSubCategory);
    @Query("{ 'productType':{$regex: ?0, $options:'i' }}")
    List<Product> findProductsByProductType(String productType);

//    @Query("{ 'variants': { $elemMatch: { 'color': { $regex: ?0$, $options: 'i' } } } , 'productType': { $regex: ?1$, $options: 'i' }}")
//    List<Product> findAllProductByColor(String color, String productType);
//    @Query("{ 'attributes.size' : { $regex: ?0$, $options: 'i' } }")
//    List<Product> findAllProductBySize(String size);
}
