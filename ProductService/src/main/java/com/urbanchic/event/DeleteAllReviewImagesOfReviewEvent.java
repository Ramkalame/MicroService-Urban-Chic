package com.urbanchic.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class DeleteAllReviewImagesOfReviewEvent extends ApplicationEvent {
    private String reviewId;

    public DeleteAllReviewImagesOfReviewEvent(Object source,String reviewId) {
        super(source);
        this.reviewId = reviewId;
    }
}
