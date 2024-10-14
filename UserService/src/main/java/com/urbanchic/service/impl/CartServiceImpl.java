package com.urbanchic.service.impl;


import com.urbanchic.dto.CartItemDto;
import com.urbanchic.dto.ChangeCartItemQuantityDto;
import com.urbanchic.entity.Cart;
import com.urbanchic.entity.helper.CartItem;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.CartRepository;
import com.urbanchic.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;


    @Override
    public void createCart(String buyerId) {
        Cart newCart = Cart.builder()
                .buyerId(buyerId)
                .cartItemList(null)
                .build();
        cartRepository.save(newCart);
    }


    @Override
    public Cart addProductToCart(CartItemDto cartItemDto, String buyerId) {
        Cart existingCart = cartRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("Cart Not Found"));
        if (existingCart.getCartItemList() == null){
            List<CartItem> newCartItemList = new ArrayList<>();
            CartItem newCartItem = CartItem.builder()
                    .cartItemId(UUID.randomUUID().toString().replaceAll("-","").substring(0,6))
                    .productId(cartItemDto.getProductId())
                    .productName(cartItemDto.getProductName())
                    .productPrice(cartItemDto.getProductPrice())
                    .productAvgRating(cartItemDto.getProductAvgRating())
                    .productThumbnailUrl(cartItemDto.getProductThumbnailUrl())
                    .cartItemQuantity(cartItemDto.getCartItemQuantity())
                    .build();
            newCartItemList.add(newCartItem);
            existingCart.setCartItemList(newCartItemList);
        }else {
            CartItem newCartItem = CartItem.builder()
                    .cartItemId(UUID.randomUUID().toString().replaceAll("-","").substring(0,6))
                    .productId(cartItemDto.getProductId())
                    .productName(cartItemDto.getProductName())
                    .productPrice(cartItemDto.getProductPrice())
                    .productAvgRating(cartItemDto.getProductAvgRating())
                    .productThumbnailUrl(cartItemDto.getProductThumbnailUrl())
                    .cartItemQuantity(cartItemDto.getCartItemQuantity())
                    .build();
            existingCart.getCartItemList().add(newCartItem);
        }

        return cartRepository.save(existingCart);
    }

    @Override
    public String removeProductFromCart(String cartItemId, String buyerId) {
        Cart existingCart = cartRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("Cart Not Found"));
        existingCart.getCartItemList().removeIf(cartItem -> cartItemId.equals(cartItem.getCartItemId()));
        cartRepository.save(existingCart);
        return "removed item from cart";
    }

    @Override
    public String changeCartItemQuantity(ChangeCartItemQuantityDto changeCartItemQuantityDto) {
        Cart existingCart = cartRepository.findByBuyerId(changeCartItemQuantityDto.getBuyerId()).orElseThrow(() ->
                new EntityNotFoundException("Cart Not Found"));
        existingCart.getCartItemList().stream()
                .filter(cartItem -> cartItem.getCartItemId().equals(changeCartItemQuantityDto.getCartItemId()))
                .findFirst()
                .ifPresent(cartItem -> cartItem.setCartItemQuantity(changeCartItemQuantityDto.getCartItemQuantity()));
        cartRepository.save(existingCart);
        return "Changed the Cart Item Quantity";
    }

    @Override
    public Cart getBuyerCart(String buyerId) {
        Cart existingCart = cartRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("Cart Not Found"));
        return existingCart;
    }

    @Override
    public void deleteBuyerCart(String buyerId) {
        Cart existingCart = cartRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("Cart Not Found"));
        cartRepository.delete(existingCart);
    }




}
