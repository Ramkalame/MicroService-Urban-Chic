package com.urbanchic.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    @NotBlank(message = "ProductId is mandatory")
    private String productId;
    @NotBlank(message = "Product Name is mandatory")
    private String productName;
    @NotNull(message = "Product price is mandatory")
    private double productPrice;
    @NotNull(message = "Product average rating is mandatory")
    private double productAvgRating;
    @NotBlank(message = "Product Thumbnail image url is mandatory")
    private String productThumbnailUrl;
    @NotNull(message = "Cart Item quantity is mandatory")
    @Min(value = 1)
    @Max(value = 20)
    private int cartItemQuantity;

}
