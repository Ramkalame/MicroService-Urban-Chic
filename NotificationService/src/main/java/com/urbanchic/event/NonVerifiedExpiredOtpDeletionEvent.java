package com.urbanchic.event;

import org.springframework.context.ApplicationEvent;

public class NonVerifiedExpiredOtpDeletionEvent extends ApplicationEvent {
    public NonVerifiedExpiredOtpDeletionEvent(Object source) {
        super(source);
    }
}
