package com.urbanchic.service;

import com.urbanchic.dto.CartDto;
import com.urbanchic.dto.CartProductDto;
import com.urbanchic.entity.Cart;

import java.util.List;

public interface CartService {

    Cart addProductToCart(CartDto cartDto);
    String removeProductFromCart(String cartItemId);
    String changeQuantity(String cartItemId, Integer productQuantity);
    List<CartProductDto> getAllCartProducts(String userId);
}
