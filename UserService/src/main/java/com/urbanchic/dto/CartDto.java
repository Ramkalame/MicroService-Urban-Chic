package com.urbanchic.dto;

import com.urbanchic.entity.helper.CartItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    @NotBlank(message = "buyerId is mandatory")
    private String buyerId;
    private List<CartItemDto> cartItemList;
}
