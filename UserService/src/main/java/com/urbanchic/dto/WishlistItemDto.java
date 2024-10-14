package com.urbanchic.dto;

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
public class WishlistItemDto {

    @NotBlank(message = "Product Id is mandatory")
    private String productId;
    @NotBlank(message = "Product name is mandatory")
    private String productName;
    @NotNull(message = "Product Price is mandatory")
    private double productPrice;
    @NotNull(message = "Product average rating is mandatory")
    private double productAvgRating;
    @NotBlank(message = "Product thumbnail image is mandatory")
    private String productThumbnailUrl;

}
