package com.urbanchic.service.impl;

import com.urbanchic.external.PurchasedOrderDto;
import com.urbanchic.service.MailService;
import com.urbanchic.service.MessageConsumer;
import com.urbanchic.service.SmsService;
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
    private final SmsService smsService;

    @Override
    @RabbitListener(queues ={"${consumer.purchase-order-mail.queue-name}"} )
    public void purchaseOrderMailQueueConsumer(PurchasedOrderDto purchasedOrderEmailDto){
            mailService.sendPurchaseOrderMail(purchasedOrderEmailDto);
            LOGGER.info("Info {}: ","Mail is sent");
    }

    @Override
    @RabbitListener(queues = {"${consumer.purchase-order-sms.queue-name}"})
    public void purchaseOrderSmsQueueConsumer(PurchasedOrderDto purchasedOrderDto) {
            smsService.sendPurchaseOrderSms(purchasedOrderDto);
            LOGGER.info("Info {}: ","Sms is sent");
    }


}
