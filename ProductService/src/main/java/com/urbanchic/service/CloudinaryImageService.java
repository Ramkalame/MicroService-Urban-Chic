package com.urbanchic.service;

import com.urbanchic.dto.ProductImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CloudinaryImageService {

    List<ProductImageDto> uploadIImage(String productId,List<MultipartFile> files);
    String deleteProductImage(String publicId);

}
