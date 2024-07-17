package com.urbanchic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product_collection")
public class Product {

    @Id
    private String productId;
    private String productName;
    private Double productPrice;
    private String productDescription;
    private String productImageUrl;
    private Integer productQuantity;
    private String productBrand;
    private String productCategory;
    private String productSubCategory;
    private String productType;
    private String sellerId;
    private Map<String,Object> attributes;
    private Map<String,Object> [] variants;

}
