package com.urbanchic.service;

import com.urbanchic.dto.ImageUploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImageService {

    ImageUploadResponseDto uploadIImage(MultipartFile file);
    String deleteImage(String publicId);
    ImageUploadResponseDto  updateImage(String sellerId,MultipartFile file);
}
