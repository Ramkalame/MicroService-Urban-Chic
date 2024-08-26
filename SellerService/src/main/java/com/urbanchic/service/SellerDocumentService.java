package com.urbanchic.service;

import com.urbanchic.entity.SellerDocument;
import com.urbanchic.dto.SellerDocumentDto;

public interface SellerDocumentService {

    public SellerDocument addSellerDocument(SellerDocumentDto sellerDocumentDto);
    public boolean approveVerificationRequest(String sellerId);
    public boolean rejectVerificationRequest(String sellerId);
}
