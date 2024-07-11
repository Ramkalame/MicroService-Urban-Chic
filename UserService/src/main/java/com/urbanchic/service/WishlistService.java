package com.urbanchic.service;

import com.urbanchic.dto.WishlistDto;
import com.urbanchic.dto.WishlistProductDto;
import com.urbanchic.entity.Wishlist;

import java.util.List;

public interface WishlistService {

    Wishlist addItemToWishlist(WishlistDto wishlistDto);
    String removeItemFromWishlist(String wishlistItemId);
    List<WishlistProductDto> getAllWishlistItemOfUser(String mobileNo);

}
