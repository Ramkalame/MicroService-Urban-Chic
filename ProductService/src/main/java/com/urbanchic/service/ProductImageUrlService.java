package com.urbanchic.service;

import com.urbanchic.dto.ProductImageUrlDto;
import com.urbanchic.entity.ProductImageUrl;
import com.urbanchic.event.DeleteAllPrductImagesEvent;

import java.util.List;

public interface ProductImageUrlService {

    ProductImageUrl addProductImageUrl(ProductImageUrlDto productImageUrlDto);
    List<ProductImageUrl> getAllProductImageUrlByProductId(String productId);
    String deleteProductImageUrlById(String id);
    ProductImageUrl getProductImageUrlById(String id);
    void deleteAllProductImage(DeleteAllPrductImagesEvent deleteAllPrductImagesEvent);

}
