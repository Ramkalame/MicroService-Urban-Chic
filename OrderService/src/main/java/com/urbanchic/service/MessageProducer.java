package com.urbanchic.service;

import com.urbanchic.messageDto.PurchasedOrderDto;

public interface MessageProducer {

    void sendPurchaseOrderMail(PurchasedOrderDto purchasedOrderDto);
    void sendPurchaseOrderSms(PurchasedOrderDto purchasedOrderDto);
}
