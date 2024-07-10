package com.urbanchic.controller;

import com.urbanchic.dto.WishlistDto;
import com.urbanchic.dto.WishlistProductDto;
import com.urbanchic.entity.Wishlist;
import com.urbanchic.service.WishlistService;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<?> addItemToWishlist(@RequestBody @Valid WishlistDto wishlistDto){
        Wishlist responseData = wishlistService.addItemToWishlist(wishlistDto);

        ApiResponse<Wishlist> apiResponse = ApiResponse.<Wishlist>builder()
                .data(responseData)
                .message("Product is added to wishlist")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();

        return  ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/delete/{wishlistItemId}")
    public ResponseEntity<?> removeItemFromWishlist(@PathVariable("wishlistItemId") String wishlistItemId){
        String responseData = wishlistService.removeItemFromWishlist(wishlistItemId);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message("Product is deleted from wishlist")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{mobileNo}")
    public ResponseEntity<?> getAllWishlistItemOfUser(@PathVariable("mobileNo") String mobileNo){
        List<WishlistProductDto> responseData = wishlistService.getAllWishlistItemOfUser(mobileNo);

        ApiResponse<List<WishlistProductDto>> apiResponse = ApiResponse.<List<WishlistProductDto>>builder()
                .data(responseData)
                .message("Wishlist Item")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
