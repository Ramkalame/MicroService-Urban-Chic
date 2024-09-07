package com.urbanchic.service;

import com.urbanchic.dto.ImageUploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudinaryImageService {

    ImageUploadResponseDto uploadIImage(MultipartFile file);
    String deleteImage(String publicId);
}
