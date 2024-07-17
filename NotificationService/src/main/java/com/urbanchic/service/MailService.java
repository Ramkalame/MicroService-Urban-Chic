package com.urbanchic.service;

import com.urbanchic.external.PurchasedOrderDto;

public interface MailService {

    void sendPurchaseOrderMail(PurchasedOrderDto purchasedOrderEmailDto);
}
