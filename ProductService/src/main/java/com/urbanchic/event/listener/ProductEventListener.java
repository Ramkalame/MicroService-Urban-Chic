package com.urbanchic.event.listener;

import com.urbanchic.dto.ProductImageDto;
import com.urbanchic.event.UploadProductImageEvent;
import com.urbanchic.service.CloudinaryImageService;
import com.urbanchic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductEventListener {

    private final ProductService productService;

    @Async
    @EventListener
    public void UploadProductImageEventListener(UploadProductImageEvent event){
        productService.addProductImage(event.getProductId(), event.getProductImageDtoList());
    }


}
