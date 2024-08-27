package com.urbanchic.service.impl;

import com.urbanchic.dto.SellerDocumentDto;
import com.urbanchic.entity.SellerDocument;
import com.urbanchic.entity.sellerEnum.SellerStatus;
import com.urbanchic.event.SellerDocumentAndAddressDeleteEvent;
import com.urbanchic.exception.EntityAlreadyExistException;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.SellerDocumentRepository;
import com.urbanchic.service.SellerAddressService;
import com.urbanchic.service.SellerDocumentService;
import com.urbanchic.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerDocumentServiceImpl implements SellerDocumentService {

    private final SellerDocumentRepository sellerDocumentRepository;
    private final SellerAddressService sellerAddressService;
    private final SellerService sellerService;

    @Override
    public SellerDocument addSellerDocument(SellerDocumentDto sellerDocumentDto) {
        SellerDocument existingSellerDocument = sellerDocumentRepository
                .findByGstNumber(sellerDocumentDto.getGstNumber()).orElse(null);
        if (existingSellerDocument != null){
            throw new EntityAlreadyExistException("GST Number is already used");
        }

        SellerDocument newSellerDocument = SellerDocument.builder()
                .companyName(sellerDocumentDto.getCompanyName())
                .companyLogoUrl(sellerDocumentDto.getCompanyLogoUrl())
                .gstNumber(sellerDocumentDto.getGstNumber())
                .panNumber(sellerDocumentDto.getPanNumber())
                .accountNumber(sellerDocumentDto.getAccountNumber())
                .ifscCode(sellerDocumentDto.getIfscCode())
                .sellerId(sellerDocumentDto.getSellerId())
                .build();
        SellerDocument savedSellerDocument = sellerDocumentRepository.save(newSellerDocument);
        sellerService.updateSellerAccountStatus(sellerDocumentDto.getSellerId(), SellerStatus.COMPANY_DETAILS_VERIFIED.name());
        sellerAddressService.addSellerAddress(sellerDocumentDto.getSellerAddress());
        return savedSellerDocument;
    }

    @Override
    public SellerDocument updateSellerDocument(String id, SellerDocumentDto sellerDocumentDto) {
        SellerDocument existingSellerDocument = sellerDocumentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Seller Address Not Found"));

        existingSellerDocument.setCompanyName(sellerDocumentDto.getCompanyName());
        existingSellerDocument.setCompanyLogoUrl(sellerDocumentDto.getCompanyLogoUrl());
        existingSellerDocument.setGstNumber(sellerDocumentDto.getGstNumber());
        existingSellerDocument.setPanNumber(sellerDocumentDto.getPanNumber());
        existingSellerDocument.setAccountNumber(sellerDocumentDto.getAccountNumber());
        existingSellerDocument.setIfscCode(sellerDocumentDto.getIfscCode());
        SellerDocument updatedSellerDocument = sellerDocumentRepository.save(existingSellerDocument);
        return updatedSellerDocument;
    }

    @Override
    public SellerDocument getSellerDocumentBySellerId(String sellerId) {
        SellerDocument existingSellerDocument = sellerDocumentRepository.findBySellerId(sellerId).orElseThrow(() ->
                new EntityNotFoundException("Seller Address Not Found"));
        return existingSellerDocument;
    }

    @Override
    @Async
    @EventListener
    public String deleteSellerDocument(SellerDocumentAndAddressDeleteEvent event) {
        SellerDocument existingSellerDocument = sellerDocumentRepository.findBySellerId(event.getSellerId()).orElseThrow(() ->
                new EntityNotFoundException("Seller Address Not Found"));
        sellerDocumentRepository.delete(existingSellerDocument);
        return "Seller Id: "+event.getSellerId();
    }
}
