package com.urbanchic.service;

import com.urbanchic.dto.ImageUploadResponseDto;
import com.urbanchic.dto.SellerDocumentDto;
import com.urbanchic.dto.UpdateSellerDocumentDto;
import com.urbanchic.entity.SellerDocument;
import com.urbanchic.event.SellerDocumentAndAddressDeleteEvent;

public interface SellerDocumentService {

    SellerDocument addSellerDocument(SellerDocumentDto sellerDocumentDto);
    SellerDocument updateSellerDocument(String sellerId, UpdateSellerDocumentDto sellerDocumentDto);
    SellerDocument getSellerDocumentBySellerId(String sellerId);
    String deleteSellerDocument(SellerDocumentAndAddressDeleteEvent event);
    SellerDocument updateSellerImage(String sellerId, ImageUploadResponseDto imageUploadResponseDto);

}
