package com.urbanchic.service;

import com.urbanchic.model.SellerDocument;
import com.urbanchic.modelDTO.SellerDocumentDto;

public interface SellerDocumentService {

    public SellerDocument addSellerDocument(SellerDocumentDto sellerDocumentDto);
    public boolean approveVerificationRequest(String sellerId);
    public boolean rejectVerificationRequest(String sellerId);
}
