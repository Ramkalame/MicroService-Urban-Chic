package com.urbanchic.event;

import com.urbanchic.entity.helper.ProductImage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.List;


@Getter
@Setter
public class DeleteAllProductImagesEvent extends ApplicationEvent {

    private List<ProductImage> productImageList;

    public DeleteAllProductImagesEvent(Object source, List<ProductImage> productImageList) {
        super(source);
        this.productImageList = productImageList;
    }
}
