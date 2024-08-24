package com.urbanchic.service.impl;

import com.urbanchic.external.PurchasedOrderDto;
import com.urbanchic.service.MailService;
import com.urbanchic.service.ConsumerService;
import com.urbanchic.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final MailService mailService;
    private final SmsService smsService;

    @Override
    @RabbitListener(queues ={"${consumer.purchase-order-mail.queue-name}"} )
    public void purchaseOrderMailQueueConsumer(PurchasedOrderDto purchasedOrderEmailDto){
            mailService.sendPurchaseOrderMail(purchasedOrderEmailDto);
            log.info("Purchase Order Mail sent to : {}",purchasedOrderEmailDto.getBuyerName());
    }

    @Override
    @RabbitListener(queues = {"${consumer.purchase-order-sms.queue-name}"})
    public void purchaseOrderSmsQueueConsumer(PurchasedOrderDto purchasedOrderSmsDto) {
            smsService.sendPurchaseOrderSms(purchasedOrderSmsDto);
            log.info("Purchase Order SMS sent to : {}",purchasedOrderSmsDto.getBuyerName());
    }


}
