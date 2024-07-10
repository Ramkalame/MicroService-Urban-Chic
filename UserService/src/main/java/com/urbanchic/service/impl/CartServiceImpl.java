package com.urbanchic.service.impl;

import com.urbanchic.client.ProductServiceClient;
import com.urbanchic.dto.CartDto;
import com.urbanchic.dto.CartProductDto;
import com.urbanchic.entity.Cart;
import com.urbanchic.exception.ProductAlreadyExistsException;
import com.urbanchic.exception.ProductNotFoundException;
import com.urbanchic.exception.EmptyException;
import com.urbanchic.external.Product;
import com.urbanchic.repository.CartRepository;
import com.urbanchic.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductServiceClient productServiceClient;

    @Override
    public Cart addProductToCart(CartDto cartDto) {
        List<Cart> cartList = cartRepository.findAllByMobileNo(cartDto.getMobileNo());
        if (!cartList.isEmpty()){
            boolean ifProductExists = cartList.stream().anyMatch(cartItem ->
                    cartItem.getProductId().equals(cartDto.getProductId()));
             if (ifProductExists){
                 throw  new ProductAlreadyExistsException("Product is Already Added in Cart");
             }
            Cart newCart = Cart.builder()
                    .productId(cartDto.getProductId())
                    .productQuantity(1)
                    .mobileNo(cartDto.getMobileNo())
                    .build();
            return cartRepository.save(newCart);
        }
        Cart newCart = Cart.builder()
                .productId(cartDto.getProductId())
                .productQuantity(1)
                .mobileNo(cartDto.getMobileNo())
                .build();
        return cartRepository.save(newCart);
    }

    @Override
    public String removeProductFromCart(String cartItemId) {
        Cart cart = cartRepository.findById(cartItemId).orElseThrow(()->
                new ProductNotFoundException("Item does not exist"));
        cartRepository.delete(cart);
        return "Item removed";
    }

    @Override
    public String changeQuantity(String cartItemId, Integer productQuantity) {
        Cart cart = cartRepository.findById(cartItemId).orElseThrow(()->
                new ProductNotFoundException("Item does not exist"));
        cart.setProductQuantity(productQuantity);
        cartRepository.save(cart);
        return "Changed the Quantity to : "+productQuantity;
    }

    @Override
    public List<CartProductDto> getAllCartProducts(String mobileNo) {

        List<Cart> cartItems = cartRepository.findAllByMobileNo(mobileNo);
        if (cartItems.isEmpty()){
            throw  new EmptyException("Cart is Empty");
        }

        List<CartProductDto> cartProductList = new ArrayList<>();
        for (Cart cart:cartItems){
            Product product = productServiceClient.getProductById(cart.getProductId()).getBody().getData();

            CartProductDto cartProductDto = CartProductDto.builder()
                    .cartItemId(cart.getCartItemId())
                    .productQuantity(cart.getProductQuantity())
                    .product(product)
                    .build();
            cartProductList.add(cartProductDto);
        }
        return cartProductList;
    }
}
