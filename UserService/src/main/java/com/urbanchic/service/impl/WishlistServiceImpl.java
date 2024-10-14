package com.urbanchic.service.impl;

import com.urbanchic.dto.WishlistItemDto;
import com.urbanchic.entity.Wishlist;
import com.urbanchic.entity.helper.WishlistItem;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.WishlistRepository;
import com.urbanchic.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    @Override
    public void createWishlist(String buyerId) {
       Wishlist newWishlist = Wishlist.builder()
               .buyerId(buyerId)
               .wishlistItemList(null)
               .build();
       wishlistRepository.save(newWishlist);
    }

    @Override
    public Wishlist addItemToWishlist(WishlistItemDto wishlistItemDto,String buyerId) {
        Wishlist existingWishlist = wishlistRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("Wishlist Not Found"));
        if (existingWishlist.getWishlistItemList() == null){
            List<WishlistItem> newWishlistItemList = new ArrayList<>();
            WishlistItem newWishlistItem = WishlistItem.builder()
                    .wishlistItemId(UUID.randomUUID().toString().replaceAll("-","").substring(0,6))
                    .productId(wishlistItemDto.getProductId())
                    .productName(wishlistItemDto.getProductName())
                    .productPrice(wishlistItemDto.getProductPrice())
                    .productAvgRating(wishlistItemDto.getProductAvgRating())
                    .productThumbnailUrl(wishlistItemDto.getProductThumbnailUrl())
                    .build();
            newWishlistItemList.add(newWishlistItem);
            existingWishlist.setWishlistItemList(newWishlistItemList);
        }else {
            WishlistItem newWishlistItem = WishlistItem.builder()
                    .wishlistItemId(UUID.randomUUID().toString().replaceAll("-","").substring(0,6))
                    .productId(wishlistItemDto.getProductId())
                    .productName(wishlistItemDto.getProductName())
                    .productPrice(wishlistItemDto.getProductPrice())
                    .productAvgRating(wishlistItemDto.getProductAvgRating())
                    .productThumbnailUrl(wishlistItemDto.getProductThumbnailUrl())
                    .build();
            existingWishlist.getWishlistItemList().add(newWishlistItem);
        }
        return wishlistRepository.save(existingWishlist);
    }

    @Override
    public String removeItemFromWishlist(String wishlistItemId,String buyerId) {
        Wishlist existingWishlist = wishlistRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("Wishlist Not Found"));
        existingWishlist.getWishlistItemList().removeIf(wishlistItem -> wishlistItemId.equals(wishlistItem.getWishlistItemId()));
        wishlistRepository.save(existingWishlist);
        return "Deleted Wishlist Item Successfully";
    }

    @Override
    public Wishlist getBuyerWishlist(String buyerId) {
        Wishlist existingWishlist = wishlistRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("Wishlist Not Found"));
        return existingWishlist;
    }

    @Override
    public void deleteBuyerWishlist(String buyerId) {
        Wishlist existingWishlist = wishlistRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("Wishlist Not Found"));
        wishlistRepository.delete(existingWishlist);
    }


}
