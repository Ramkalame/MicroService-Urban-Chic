package com.urbanchic.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class DeleteAllReviewsOfProductEvent extends ApplicationEvent {
    private String productId;
    public DeleteAllReviewsOfProductEvent(Object source,String productId) {
        super(source);
        this.productId = productId;
    }
}
