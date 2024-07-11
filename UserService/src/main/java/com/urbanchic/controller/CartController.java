package com.urbanchic.controller;

import com.urbanchic.dto.CartDto;
import com.urbanchic.dto.CartProductDto;
import com.urbanchic.entity.Cart;
import com.urbanchic.service.CartService;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addProductToCart(@RequestBody @Valid CartDto cartDto){
        Cart responseData = cartService.addProductToCart(cartDto);

        ApiResponse<Cart> apiResponse = ApiResponse.<Cart>builder()
                .data(responseData)
                .message("Product is added to cart")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();

        return  ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    @GetMapping("/show/{mobileNo}")
    public ResponseEntity<?> getAllCartProducts(@PathVariable("mobileNo") String mobileNo){
        List<CartProductDto> responseData = cartService.getAllCartProducts(mobileNo);

        ApiResponse<List<CartProductDto>> apiResponse = ApiResponse.<List<CartProductDto>>builder()
                .data(responseData)
                .message("Product list of cart in user "+mobileNo)
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/change/Quantity/{cartItemId}/{productQuantity}")
    public ResponseEntity<?> changeQuantity(@PathVariable("cartItemId") String cartItemId,
                                            @PathVariable("productQuantity") Integer productQuantity){
        String responseData = cartService.changeQuantity(cartItemId,productQuantity);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message("Changed Quantity")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<?> removeCartItem(@PathVariable("cartItemId") String cartItemId){
        String responseData = cartService.removeProductFromCart(cartItemId);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message("Item removed from cart")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
