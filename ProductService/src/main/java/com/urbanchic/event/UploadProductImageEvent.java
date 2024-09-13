package com.urbanchic.event;

import com.urbanchic.dto.ProductImageDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class UploadProductImageEvent  extends ApplicationEvent {
    private String productId;
    private List<ProductImageDto> productImageDtoList;

    public UploadProductImageEvent(Object source,String productId,List<ProductImageDto> productImageDtoList) {
        super(source);
        this.productId = productId;
        this.productImageDtoList = productImageDtoList;
    }

}
