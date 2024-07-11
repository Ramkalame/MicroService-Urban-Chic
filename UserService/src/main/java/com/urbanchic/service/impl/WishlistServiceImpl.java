package com.urbanchic.service.impl;

import com.urbanchic.client.ProductServiceClient;
import com.urbanchic.dto.WishlistDto;
import com.urbanchic.dto.WishlistProductDto;
import com.urbanchic.entity.Wishlist;
import com.urbanchic.exception.EmptyException;
import com.urbanchic.exception.ProductAlreadyExistsException;
import com.urbanchic.exception.ProductNotFoundException;
import com.urbanchic.external.Product;
import com.urbanchic.repository.WishlistRepository;
import com.urbanchic.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductServiceClient productServiceClient;

    @Override
    public Wishlist addItemToWishlist(WishlistDto wishlistDto) {
        List<Wishlist> wishlistList = wishlistRepository.findAllByMobileNo(wishlistDto.getMobileNo());
        if (!wishlistList.isEmpty()){
            boolean ifProductExists = wishlistList.stream().anyMatch(wishlistItem ->
                    wishlistItem.getProductId().equals(wishlistDto.getProductId()));
            if (ifProductExists){
                throw  new ProductAlreadyExistsException("Product is Already Added in Wishlist");
            }
            Wishlist newWishlistItem = Wishlist.builder()
                    .productId(wishlistDto.getProductId())
                    .mobileNo(wishlistDto.getMobileNo())
                    .build();
            return wishlistRepository.save(newWishlistItem);
        }
        Wishlist newWishlistItem = Wishlist.builder()
                .productId(wishlistDto.getProductId())
                .mobileNo(wishlistDto.getMobileNo())
                .build();
        return wishlistRepository.save(newWishlistItem);
    }

    @Override
    public String removeItemFromWishlist(String wishlistItemId) {
        Wishlist existingWishlist = wishlistRepository.findById(wishlistItemId).orElseThrow(()->
                new ProductNotFoundException("Wishlist Item Does Not Exists"));
        wishlistRepository.delete(existingWishlist);
        return "Deleted Wishlist Item Successfully";
    }

    @Override
    public List<WishlistProductDto> getAllWishlistItemOfUser(String mobileNo) {
        List<Wishlist> wishlistList = wishlistRepository.findAllByMobileNo(mobileNo);
        if (wishlistList.isEmpty()){
            throw new EmptyException("Empty Wishlist");
        }
        List<WishlistProductDto> wishlistProductDtos = new ArrayList<>();
        for (Wishlist wishlist:wishlistList){
            Product product = productServiceClient.getProductById(wishlist.getProductId()).getBody().getData();
            WishlistProductDto wishlistProductDto = WishlistProductDto.builder()
                    .wishlistItemId(wishlist.getWishlistItemId())
                    .product(product)
                    .build();
            wishlistProductDtos.add(wishlistProductDto);
        }

        return wishlistProductDtos;
    }


}
