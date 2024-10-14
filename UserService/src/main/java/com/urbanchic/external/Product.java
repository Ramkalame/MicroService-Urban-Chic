package com.urbanchic.external;

import com.urbanchic.external.helper.Attribute;
import com.urbanchic.external.helper.ProductImage;
import com.urbanchic.external.helper.Variant;
import com.urbanchic.external.productenum.ProductColor;
import com.urbanchic.external.productenum.ProductSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private LocalDateTime addedDate;

}
