package com.urbanchic.controller;

import com.urbanchic.dto.CartItemDto;
import com.urbanchic.dto.ChangeCartItemQuantityDto;
import com.urbanchic.entity.Cart;
import com.urbanchic.service.CartService;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/buyers/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{buyerId}/add-item")
    public ResponseEntity<?> addProductToCart(@RequestBody @Valid CartItemDto cartItemDto,@PathVariable("buyerId") String buyerId){
        Cart responseData = cartService.addProductToCart(cartItemDto,buyerId);

        ApiResponse<Cart> apiResponse = ApiResponse.<Cart>builder()
                .data(responseData)
                .message("Product is added to cart")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();

        return  ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/{buyerId}/items/{cartItemId}")
    public ResponseEntity<?> removeCartItem(@PathVariable("cartItemId") String cartItemId,@PathVariable("buyerId") String buyerId){
        String responseData = cartService.removeProductFromCart(cartItemId,buyerId);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message("Item removed from cart")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PatchMapping("/item-quantity")
    public ResponseEntity<?> changeCartItemQuantity(@RequestBody @Valid ChangeCartItemQuantityDto changeCartItemQuantityDto){
        String responseData = cartService.changeCartItemQuantity(changeCartItemQuantityDto);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message("Changed Quantity successfully")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{buyerId}")
    public ResponseEntity<?> getBuyerCart(@PathVariable("buyerId") String buyerId){
        Cart responseData = cartService.getBuyerCart(buyerId);

        ApiResponse<Cart> apiResponse = ApiResponse.<Cart>builder()
                .data(responseData)
                .message("Buyers Cart Fetched Successfully")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
