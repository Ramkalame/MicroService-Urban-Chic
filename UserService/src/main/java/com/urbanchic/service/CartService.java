package com.urbanchic.service;

import com.urbanchic.dto.CartItemDto;
import com.urbanchic.dto.ChangeCartItemQuantityDto;
import com.urbanchic.entity.Cart;

public interface CartService {

    void createCart(String buyerId);
    Cart addProductToCart(CartItemDto cartItemDto,String buyerId);
    String removeProductFromCart(String cartItemId,String buyerId);
    String changeCartItemQuantity(ChangeCartItemQuantityDto changeCartItemQuantityDto);
    Cart getBuyerCart(String buyerId);
    void deleteBuyerCart(String buyerId);

}
