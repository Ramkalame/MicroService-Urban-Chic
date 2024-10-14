package com.urbanchic.entity.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private String cartItemId;
    private String productId;
    private String productName;
    private double productPrice;
    private double productAvgRating;
    private String productThumbnailUrl;
    private int cartItemQuantity;

}
