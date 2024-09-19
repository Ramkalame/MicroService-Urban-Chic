package com.urbanchic.event.listener;

import com.urbanchic.dto.ProductImageDto;
import com.urbanchic.entity.Product;
import com.urbanchic.entity.ProductImage;
import com.urbanchic.event.DeleteAllProductImagesEvent;
import com.urbanchic.event.DeleteAllReviewsOfProductEvent;
import com.urbanchic.event.UploadProductImageEvent;
import com.urbanchic.service.CloudinaryImageService;
import com.urbanchic.service.ProductService;
import com.urbanchic.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductEventListener {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final CloudinaryImageService cloudinaryImageService;

    @Async
    @EventListener
    public void UploadProductImageEventListener(UploadProductImageEvent event){
        productService.addProductImage(event.getProductId(), event.getProductImageDtoList());
    }

    @Async
    @EventListener
    public void deleteAllReviewEventListener(DeleteAllReviewsOfProductEvent event){
        reviewService.deleteAllReviewByProductId(event.getProductId());
    }

    @Async
    @EventListener
    public void deleteAllProductImagesEventListener(DeleteAllProductImagesEvent event){
        for (ProductImage productImage: event.getProductImageList()){
            cloudinaryImageService.deleteProductImage(productImage.getPublicId());
        }
    }





}
