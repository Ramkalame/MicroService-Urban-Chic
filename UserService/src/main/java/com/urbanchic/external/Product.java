package com.urbanchic.external;

import com.urbanchic.external.productenum.ProductColor;
import com.urbanchic.external.productenum.ProductSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

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

}
