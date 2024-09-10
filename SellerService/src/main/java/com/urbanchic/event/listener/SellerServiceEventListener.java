package com.urbanchic.event.listener;

import com.urbanchic.clients.AuthServiceClient;
import com.urbanchic.event.UpdateSellerAccountStatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SellerServiceEventListener {

    private final AuthServiceClient authServiceClient;

    @Async
    @EventListener
    private void updateSellerAccountStatusEventListener(UpdateSellerAccountStatusEvent event){
        log.info("updated seller account status event is listened");
        authServiceClient.updateSellerAccountStatus(event.getSellerId());
        log.info("update seller account status event is completed");
    }

}
