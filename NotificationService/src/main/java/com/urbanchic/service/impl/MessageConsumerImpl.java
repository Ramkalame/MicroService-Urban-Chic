package com.urbanchic.service.impl;

import com.urbanchic.external.PurchasedOrderEmailDto;
import com.urbanchic.service.MailService;
import com.urbanchic.service.MessageConsumer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageConsumerImpl implements MessageConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageConsumerImpl.class);

    private final MailService mailService;

    @RabbitListener(queues ={"${consumer.purchase-order.queue-name}"} )
    public void purchaseOrderMailQueueConsumer(PurchasedOrderEmailDto purchasedOrderEmailDto){
            mailService.sendPurchaseOrderMail(purchasedOrderEmailDto);
            LOGGER.info("Info {}: ","Mail is sent");
    }

}
