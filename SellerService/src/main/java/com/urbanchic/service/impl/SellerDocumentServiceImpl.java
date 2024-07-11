package com.urbanchic.service.impl;

import com.urbanchic.exception.SellerNotFoundException;
import com.urbanchic.model.SellerDocument;
import com.urbanchic.model.sellerEnum.SellerStatus;
import com.urbanchic.modelDTO.SellerDocumentDto;
import com.urbanchic.repository.SellerDocumentRepository;
import com.urbanchic.service.SellerDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerDocumentServiceImpl implements SellerDocumentService {

    private final SellerDocumentRepository sellerDocumentRepository;

    @Override
    public SellerDocument addSellerDocument(SellerDocumentDto sellerDocumentDto) {
        SellerDocument document = SellerDocument.builder()
                .aadhaarImg(sellerDocumentDto.getAadhaarImg())
                .aadhaarNo(sellerDocumentDto.getAadhaarNo())
                .panImg(sellerDocumentDto.getPanImg())
                .panNo(sellerDocumentDto.getPanNo())
                .bankFrontPageImg(sellerDocumentDto.getBankFrontPageImg())
                .accountNo(sellerDocumentDto.getAccountNo())
                .ifscCode(sellerDocumentDto.getIfscCode())
                .sellerStatus(SellerStatus.PENDING_VERIFICATION)
                .sellerId(sellerDocumentDto.getSellerId())
                .build();

        return sellerDocumentRepository.save(document);
    }

    @Override
    public boolean approveVerificationRequest(String sellerId) {
        SellerDocument existringSellerDocument = sellerDocumentRepository.findBySellerId(sellerId);
        if(existringSellerDocument == null){
            throw new SellerNotFoundException("Seller not found with this email: " + sellerId);
        }
        existringSellerDocument.setSellerStatus(SellerStatus.VERIFIED);
        sellerDocumentRepository.save(existringSellerDocument);
        return true;
    }

    @Override
    public boolean rejectVerificationRequest(String sellerId) {
        SellerDocument existringSellerDocument = sellerDocumentRepository.findBySellerId(sellerId);
        if(existringSellerDocument == null){
            throw new SellerNotFoundException("Seller not found with this email: " + sellerId);
        }
        existringSellerDocument.setSellerStatus(SellerStatus.REJECTED);
        sellerDocumentRepository.save(existringSellerDocument);
        return true;
    }
}
