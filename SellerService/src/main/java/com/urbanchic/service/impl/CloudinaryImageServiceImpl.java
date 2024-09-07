package com.urbanchic.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.urbanchic.config.CloudinaryConfigDetails;
import com.urbanchic.dto.ImageUploadResponseDto;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.exception.ImageUploadFailedException;
import com.urbanchic.service.CloudinaryImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CloudinaryImageServiceImpl implements CloudinaryImageService {

    private final Cloudinary cloudinary;

    @Override
    public ImageUploadResponseDto uploadIImage(MultipartFile file) {
        ImageUploadResponseDto responseDto = new ImageUploadResponseDto();
        try{
           Map res = cloudinary.uploader().upload(file.getBytes(),
                   ObjectUtils.asMap("folder", CloudinaryConfigDetails.IMAGE_FOLDER));
           responseDto.setPublicId(res.get("public_id").toString());
           responseDto.setPublicUrl(res.get("url").toString());
        }catch (Exception e){
            throw new ImageUploadFailedException("Image Upload Failed!! Try Again");
        }
        return responseDto;
    }

    @Override
    public String deleteImage(String publicId) {
        try {
            Map res = cloudinary.uploader().destroy(publicId,ObjectUtils.emptyMap());
           if ("ok".equals(res.get("result"))){
               return "Deleted Successfully";
           }else {
               throw new EntityNotFoundException("Image Deletion Failed!! Try Again");
           }
        }catch (Exception e){
            throw new EntityNotFoundException("Image Deletion Failed!! Try Again");
        }
    }
}
