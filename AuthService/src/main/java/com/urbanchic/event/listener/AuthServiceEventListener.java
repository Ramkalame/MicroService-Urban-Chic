package com.urbanchic.event.listener;

import com.urbanchic.client.SellerServiceClient;
import com.urbanchic.event.SellerProfileCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor

public class AuthServiceEventListener {

    private final SellerServiceClient sellerServiceClient;

    @Async
    @EventListener
    private void sellerProfileCreatedEventListener(SellerProfileCreatedEvent sellerProfileCreatedEvent) {
        log.info("event is listened");
        sellerServiceClient.createSeller(sellerProfileCreatedEvent.getSellerDto());
        log.info("event is completed");
    }
}
