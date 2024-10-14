package com.urbanchic.service;

import com.urbanchic.dto.WishlistDto;
import com.urbanchic.dto.WishlistItemDto;
import com.urbanchic.entity.Wishlist;

import java.util.List;

public interface WishlistService {

    void createWishlist(String buyerId);
    Wishlist addItemToWishlist(WishlistItemDto wishlistItemDto,String buyerId);
    String removeItemFromWishlist(String wishlistItemId ,String buyerId);
    Wishlist getBuyerWishlist(String buyerId);
    void deleteBuyerWishlist(String buyerId);

}
