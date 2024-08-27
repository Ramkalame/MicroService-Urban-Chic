package com.urbanchic.service.impl;

import com.urbanchic.event.SellerDocumentAndAddressDeleteEvent;
import com.urbanchic.exception.EntityAlreadyExistException;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.entity.Seller;
import com.urbanchic.entity.sellerEnum.SellerStatus;
import com.urbanchic.dto.SellerDto;
import com.urbanchic.repository.SellerRepository;

import com.urbanchic.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    public Seller createSeller(SellerDto sellerDto) {
        if (sellerRepository.findBySellerPrimaryEmail(sellerDto.getSellerPrimaryEmail()).isPresent()){
            throw new EntityAlreadyExistException("Seller is already present with this email: "+ sellerDto.getSellerPrimaryEmail());
        }

        Seller newSeller = Seller.builder()
                .sellerFullName(sellerDto.getSellerFullName())
                .sellerPrimaryMoNumber(sellerDto.getSellerPrimaryMoNumber())
                .sellerPrimaryEmail(sellerDto.getSellerPrimaryEmail())
                .sellerAccountStatus(SellerStatus.CONTACT_DETAILS_VERIFIED.name())
                .build();
        Seller savedSeller = sellerRepository.save(newSeller);
        return savedSeller;
    }

    @Override
    public Seller updateSeller(String sellerId,SellerDto sellerDto) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() ->
                new EntityNotFoundException("Seller Not Found"));
        seller.setSellerFullName(sellerDto.getSellerFullName());
        seller.setSellerPrimaryMoNumber(sellerDto.getSellerPrimaryMoNumber());
        seller.setSellerPrimaryEmail(sellerDto.getSellerPrimaryEmail());
        Seller updatedSellerDetails = sellerRepository.save(seller);
        return updatedSellerDetails;
    }

    @Override
    public Seller updateSellerAccountStatus(String sellerId, String accountStatus) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() ->
                new EntityNotFoundException("Seller Not Found"));
        seller.setSellerAccountStatus(accountStatus);
        Seller updatedSeller = sellerRepository.save(seller);
        return  updatedSeller;
    }

    @Override
    public Seller getSellerBySellerId(String sellerId) {
        Seller existingSeller = sellerRepository.findById(sellerId).orElseThrow(()->
                new EntityNotFoundException("Seller not found"));
        return existingSeller;
    }

    @Override
    public List<Seller> getAllSellerByAccountStatus(String accountStatus) {
        List<Seller> sellerList = sellerRepository.findBySellerAccountStatus(accountStatus);
        if (sellerList.isEmpty()){
            throw  new EntityNotFoundException("No Seller Found With Status:- "+accountStatus);
        }
        return  sellerList;
    }

    @Override
    public Seller getSellerByPrimaryEmail(String primaryEmail) {
        Seller seller = sellerRepository.findBySellerPrimaryEmail(primaryEmail).orElseThrow( ()->
                new EntityNotFoundException("Seller Not Found with email:- "+primaryEmail));
        return  seller;
    }

    @Override
    public String deleteSeller(String sellerId) {
        Seller existingSeller = sellerRepository.findById(sellerId).orElseThrow(()->
                new EntityNotFoundException("Seller not found"));
        publisher.publishEvent(new SellerDocumentAndAddressDeleteEvent(this,sellerId));
        sellerRepository.delete(existingSeller);
        return "Seller Id: "+sellerId;
    }


}
