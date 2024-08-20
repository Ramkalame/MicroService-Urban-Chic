package com.urbanchic.dto;

import com.urbanchic.entity.productenum.ProductColor;
import com.urbanchic.entity.productenum.ProductSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotBlank(message = "Product name is mandatory")
    private String productName;

    @NotNull(message = "Product price is mandatory")
    private Double productPrice;

    @NotBlank(message = "Product description is mandatory")
    private String productDescription;

    @NotBlank(message = "Product image URL is mandatory")
    private String productImageUrl;

    @NotNull(message = "Product quantity is mandatory")
    private Integer productQuantity;

    @NotBlank(message = "Product brand is mandatory")
    private String productBrand;

    @NotBlank(message = "Product category is mandatory")
    private String productCategory;

    @NotBlank(message = "Product sub-category is mandatory")
    private String productSubCategory;

    @NotBlank(message = "Product type is mandatory")
    private String productType;

    @NotBlank(message = "Seller ID is mandatory")
    private String sellerId;

    private Map<String, Object> attributes;

    private Map<String, Object>[] variants;

    private int oneStarCount;
    private int twoStarCount;
    private int threeStarCount;
    private int fourStarCount;
    private int fiveStarCount;
}
