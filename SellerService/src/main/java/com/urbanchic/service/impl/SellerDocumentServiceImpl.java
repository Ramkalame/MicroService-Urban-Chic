package com.urbanchic.service.impl;

import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.entity.SellerDocument;
import com.urbanchic.entity.sellerEnum.SellerStatus;
import com.urbanchic.dto.SellerDocumentDto;
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
                .sellerStatus(SellerStatus.VERIFICATION_PENDING)
                .sellerId(sellerDocumentDto.getSellerId())
                .build();

        return sellerDocumentRepository.save(document);
    }

    @Override
    public boolean approveVerificationRequest(String sellerId) {
        SellerDocument existringSellerDocument = sellerDocumentRepository.findBySellerId(sellerId);
        if(existringSellerDocument == null){
            throw new EntityNotFoundException("Seller not found with this email: " + sellerId);
        }
        existringSellerDocument.setSellerStatus(SellerStatus.VERIFIED);
        sellerDocumentRepository.save(existringSellerDocument);
        return true;
    }

    @Override
    public boolean rejectVerificationRequest(String sellerId) {
        SellerDocument existringSellerDocument = sellerDocumentRepository.findBySellerId(sellerId);
        if(existringSellerDocument == null){
            throw new EntityNotFoundException("Seller not found with this email: " + sellerId);
        }
        existringSellerDocument.setSellerStatus(SellerStatus.REJECTED);
        sellerDocumentRepository.save(existringSellerDocument);
        return true;
    }
}
