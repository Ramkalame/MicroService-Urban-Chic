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
public class CartProductDto {

    private String cartItemId;
    private Integer productQuantity;
    private Product product;


}
