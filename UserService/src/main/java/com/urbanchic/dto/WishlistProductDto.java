package com.urbanchic.dto;

import com.urbanchic.external.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishlistProductDto {

    private String wishlistItemId;
    private Product product;
}
