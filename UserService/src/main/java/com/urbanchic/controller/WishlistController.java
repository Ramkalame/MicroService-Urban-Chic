package com.urbanchic.controller;

import com.urbanchic.dto.WishlistItemDto;
import com.urbanchic.entity.Wishlist;
import com.urbanchic.service.WishlistService;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/buyers/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/{buyerId}/add-item")
    public ResponseEntity<?> addItemToWishlist(@RequestBody @Valid WishlistItemDto wishlistItemDto,
                                               @PathVariable("buyerId") String buyerId){
        Wishlist responseData = wishlistService.addItemToWishlist(wishlistItemDto,buyerId);

        ApiResponse<Wishlist> apiResponse = ApiResponse.<Wishlist>builder()
                .data(responseData)
                .message("Product is added to wishlist")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();

        return  ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/{buyerId}/delete-item/{wishlistItemId}")
    public ResponseEntity<?> removeItemFromWishlist(@PathVariable("wishlistItemId") String wishlistItemId,
                                                    @PathVariable("buyerId") String buyerId){
        String responseData = wishlistService.removeItemFromWishlist(wishlistItemId, buyerId);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message("Product is deleted from wishlist")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{buyerId}")
    public ResponseEntity<?> getBuyerWishlist(@PathVariable("buyerId") String buyerId){
        Wishlist responseData = wishlistService.getBuyerWishlist(buyerId);

        ApiResponse<Wishlist> apiResponse = ApiResponse.<Wishlist>builder()
                .data(responseData)
                .message("Wishlist Fetched Successfully")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
