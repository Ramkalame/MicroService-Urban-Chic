package com.urbanchic.repository;

import com.urbanchic.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomProductRepository {

    private final MongoTemplate mongoTemplate;

    public List<Product> findAllProductsByAttributes(String attributeName, String value) {
        // Dynamically build the query with the attribute name
        Query query = new Query();
        query.addCriteria(Criteria.where("attributes." + attributeName).regex(value, "i"));
        return mongoTemplate.find(query, Product.class);
    }

    public  List<Product> findAllProductsByVariants(String variantName,String value){
        Query query = new Query();
        query.addCriteria(Criteria.where("variants")
                .elemMatch(Criteria.where(variantName).regex(value,"i")));
        return mongoTemplate.find(query,Product.class);
    }

}
