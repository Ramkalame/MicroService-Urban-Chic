package com.urbanchic.service;

import com.urbanchic.external.PurchasedOrderDto;

public interface MessageConsumer {
    void purchaseOrderMailQueueConsumer(PurchasedOrderDto purchasedOrderEmailDto);
    void purchaseOrderSmsQueueConsumer(PurchasedOrderDto purchasedOrderDto);
}
