package com.urbanchic.service.impl;

import com.urbanchic.dto.ProductImageUrlDto;
import com.urbanchic.entity.ProductImageUrl;
import com.urbanchic.event.DeleteAllPrductImagesEvent;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.ProductImageUrlRepository;
import com.urbanchic.service.ProductImageUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageUrlServiceImpl implements ProductImageUrlService {

    private final ProductImageUrlRepository productImageUrlRepository;

    @Override
    public ProductImageUrl addProductImageUrl(ProductImageUrlDto productImageUrlDto) {
        ProductImageUrl newProductImageUrl = ProductImageUrl.builder()
                .productId(productImageUrlDto.getProductId())
                .productImageUrl(productImageUrlDto.getProductImageUrl())
                .build();
        ProductImageUrl savedProductImageUrl = productImageUrlRepository.save(newProductImageUrl);
        return savedProductImageUrl;
    }

    @Override
    public List<ProductImageUrl> getAllProductImageUrlByProductId(String productId) {
        List<ProductImageUrl> productImageUrlList = productImageUrlRepository.findAllProductImageUrlByProductId(productId);
        if (productImageUrlList.isEmpty()){
            throw new EntityNotFoundException("No Image Uploaded");
        }
        return productImageUrlList;
    }

    @Override
    public String deleteProductImageUrlById(String id) {
        ProductImageUrl productImageUrl = productImageUrlRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Image Not Found."));
        productImageUrlRepository.delete(productImageUrl);
        return "Product Image Is Deleted: "+productImageUrl.getId();
    }

    @Override
    public ProductImageUrl getProductImageUrlById(String id) {
        ProductImageUrl productImageUrl = productImageUrlRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Image Not Found."));
        return  productImageUrl;
    }

    @Override
    @Async
    @EventListener
    public void deleteAllProductImage(DeleteAllPrductImagesEvent deleteAllPrductImagesEvent) {
        List<ProductImageUrl> productImageUrlList = productImageUrlRepository
                .findAllProductImageUrlByProductId(deleteAllPrductImagesEvent.getProductId());
        if(!productImageUrlList.isEmpty()){
            productImageUrlRepository.deleteAll(productImageUrlList);
        }
    }
}
