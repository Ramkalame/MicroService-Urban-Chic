package com.urbanchic.entity;

import com.urbanchic.entity.productenum.ProductColor;
import com.urbanchic.entity.productenum.ProductSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product_collection")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String productId;
    private String productName;
    private ProductColor productColor;
    private Double productPrice;
    private ProductSize productSize;
    private String productDescription;
    private String productImageUrl;
    private Integer productQuantity;
    private String productBrand;
    private String productCategory;
    private String productSubCategory;
    private String productType;
    private String sellerId;
<<<<<<< Updated upstream
=======
    private Map<String,Object> attributes;
    private Map<String,Object> [] variants;
    private int oneStarCount;
    private int twoStarCount;
    private int threeStarCount;
    private int fourStarCount;
    private int fiveStarCount;
>>>>>>> Stashed changes

}
