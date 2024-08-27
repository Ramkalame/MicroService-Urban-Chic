package com.urbanchic.service.impl;

import com.urbanchic.service.ConsumerService;
import com.urbanchic.service.SmsAndEmailDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final SmsAndEmailDetailsService smsAndEmailDetailsService;

    @Override
    @RabbitListener(queues ={"${consumer.purchase-order.queue-name}"} )
    public void purchaseOrderQueueConsumer(String orderId){
        smsAndEmailDetailsService.prepareSmsAndEmailDetails(orderId);
        log.info("Order Id: {}",orderId);
    }



}
