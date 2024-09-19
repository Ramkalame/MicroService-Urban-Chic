package com.urbanchic.entity;

import com.urbanchic.entity.helper.Attribute;
import com.urbanchic.entity.helper.Variant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
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
    private String productDescription;
    private String productBrand;
    private String productCategory;
    private String productSubCategory;
    private String productType;
    private String sellerId;
    private List<Attribute> attributeList;
    private List<Variant> variantList;
    private List<ProductImage> productImageList;
    private boolean active;
    private int oneStarCount;
    private int twoStarCount;
    private int threeStarCount;
    private int fourStarCount;
    private int fiveStarCount;

}
