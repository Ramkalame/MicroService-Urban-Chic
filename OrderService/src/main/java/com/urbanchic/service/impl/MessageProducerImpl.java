package com.urbanchic.service.impl;

import com.urbanchic.service.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProducerImpl implements MessageProducer {

    //Exchange Name
    @Value("${producer.exchange-name}")
    private String orderExchangeName;

    //Routing key for purchase order mail queue
    @Value("${producer.purchase-order.routing-key}")
    private String purchaseOrderQueueRoutingKey;


    private final RabbitTemplate rabbitTemplate;
    @Override
    public void sendPurchaseOrderId(String orderId){
        rabbitTemplate.convertAndSend(orderExchangeName, purchaseOrderQueueRoutingKey, orderId);
        log.info("Message {}:","Email message sent to queue");
    }

}
