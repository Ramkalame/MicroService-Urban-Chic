package com.urbanchic.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class DeleteAllPrductImagesEvent extends ApplicationEvent {

    private String productId;

    public DeleteAllPrductImagesEvent(Object source,String productId) {
        super(source);
        this.productId = productId;
    }
}
