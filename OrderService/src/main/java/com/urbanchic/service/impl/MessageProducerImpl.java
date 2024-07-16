package com.urbanchic.service.impl;

import com.urbanchic.emailandsmsdto.PurchasedOrderEmailDto;
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
    @Value("${producer.purchase-order.routing-key}")
    private String purchaseOrderMailQueueRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendPurchaseOrderMail(PurchasedOrderEmailDto purchasedOrderEmailDto){
        LOGGER.info("Message {}:",purchasedOrderEmailDto);
        rabbitTemplate.convertAndSend(orderExchangeName,
                purchaseOrderMailQueueRoutingKey,
                purchasedOrderEmailDto);
    }

}
