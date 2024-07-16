package com.urbanchic.service;

import com.urbanchic.external.PurchasedOrderEmailDto;

public interface MailService {

    void sendPurchaseOrderMail(PurchasedOrderEmailDto purchasedOrderEmailDto);
}
