package com.urbanchic.service;

import com.urbanchic.dto.SellerDocumentDto;
import com.urbanchic.entity.SellerDocument;
import com.urbanchic.event.SellerDocumentAndAddressDeleteEvent;

public interface SellerDocumentService {

    SellerDocument addSellerDocument(SellerDocumentDto sellerDocumentDto);
    SellerDocument updateSellerDocument(String id,SellerDocumentDto sellerDocumentDto);
    SellerDocument getSellerDocumentBySellerId(String sellerId);
    String deleteSellerDocument(SellerDocumentAndAddressDeleteEvent event);

}
