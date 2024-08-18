package com.urbanchic.service.impl;

import com.urbanchic.messageDto.PurchasedOrderDto;
import com.urbanchic.service.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageProducerImpl implements MessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducerImpl.class);

    //Exchange Name
    @Value("${producer.exchange-name}")
    private String orderExchangeName;

    //Routing key for purchase order mail queue
    @Value("${producer.purchase-order-mail.routing-key}")
    private String purchaseOrderMailQueueRoutingKey;

    //Routing key for purchase order sms queue
    @Value("${producer.purchase-order-sms.routing-key}")
    private String purchaseOrderSmsQueueRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendPurchaseOrderMail(PurchasedOrderDto purchasedOrderEmailDto){
        rabbitTemplate.convertAndSend(orderExchangeName, purchaseOrderMailQueueRoutingKey, purchasedOrderEmailDto);
        LOGGER.info("Message {}:","Email message sent to queue");
    }

    @Override
    public void sendPurchaseOrderSms(PurchasedOrderDto purchasedOrderDto) {
        rabbitTemplate.convertAndSend(orderExchangeName,purchaseOrderSmsQueueRoutingKey,purchasedOrderDto);
        LOGGER.info("Message {}: ","Sms message sent queue");
    }

}
