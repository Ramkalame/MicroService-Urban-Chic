package com.urbanchic.service;

import com.urbanchic.emailandsmsdto.PurchasedOrderEmailDto;

public interface MessageProducer {

    void sendPurchaseOrderMail(PurchasedOrderEmailDto purchasedOrderEmailDto);
}
