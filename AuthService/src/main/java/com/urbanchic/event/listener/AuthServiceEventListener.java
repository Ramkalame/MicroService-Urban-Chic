package com.urbanchic.event.listener;

import com.urbanchic.client.BuyerServiceClient;
import com.urbanchic.client.SellerServiceClient;
import com.urbanchic.event.BuyerProfileCreatedEvent;
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
    private final BuyerServiceClient buyerServiceClient;

    @Async
    @EventListener
    private void sellerProfileCreatedEventListener(SellerProfileCreatedEvent event) {
        log.info("event is listened");
        sellerServiceClient.createSeller(event.getSellerDto());
        log.info("event is completed");
    }

    @Async
    @EventListener
    private void BuyerProfileCreatedEventListener(BuyerProfileCreatedEvent event){
        log.info("event is listened");
        buyerServiceClient.createBuyer(event.getBuyerDto());
        log.info("event is completed");
    }



}
