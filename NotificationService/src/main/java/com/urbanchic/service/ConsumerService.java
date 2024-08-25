package com.urbanchic.service;

import com.urbanchic.external.PurchasedOrderDto;

public interface ConsumerService {
    void purchaseOrderQueueConsumer(String orderId);
}
