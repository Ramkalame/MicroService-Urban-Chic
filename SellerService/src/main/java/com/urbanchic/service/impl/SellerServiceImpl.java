package com.urbanchic.service.impl;

import com.urbanchic.dto.SellerAddressDto;
import com.urbanchic.exception.SellerAlreadyExistException;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.entity.Seller;
import com.urbanchic.entity.SellerDocument;
import com.urbanchic.entity.sellerEnum.SellerStatus;
import com.urbanchic.dto.SellerDto;
import com.urbanchic.repository.SellerDocumentRepository;
import com.urbanchic.repository.SellerRepository;
import com.urbanchic.service.SellerAddressService;
import com.urbanchic.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final SellerAddressService sellerAddressService;

    @Override
    public Seller createSeller(SellerDto sellerDto) {
        if (sellerRepository.findBySellerPrimaryEmail(sellerDto.getSellerPrimaryEmail()).isPresent()){
            throw new SellerAlreadyExistException("Seller is already present with this email: "+ sellerDto.getSellerPrimaryEmail());
        }

        Seller newSeller = Seller.builder()
                .sellerFullName(sellerDto.getSellerFullName())
                .sellerPrimaryMoNumber(sellerDto.getSellerPrimaryMoNumber())
                .sellerPrimaryEmail(sellerDto.getSellerPrimaryEmail())
                .sellerSecondaryMoNumber(sellerDto.getSellerSecondaryMoNumber())
                .sellerSecondaryEmail(sellerDto.getSellerSecondaryEmail())
                .sellerAccountStatus(SellerStatus.VERIFICATION_PENDING.name())
                .build();
        Seller savedSeller = sellerRepository.save(newSeller);

        SellerAddressDto sellerAddressDto = sellerDto.getSellerAddress();
        sellerAddressDto.setSellerId(savedSeller.getSellerId());
        sellerAddressService.addSellerAddress(sellerAddressDto);
        return savedSeller;
    }

    @Override
    public Seller updateSeller(String sellerId,SellerDto sellerDto) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() ->
                new EntityNotFoundException("Seller Not Found"));
        seller.setSellerFullName(sellerDto.getSellerFullName());
        seller.setSellerPrimaryMoNumber(sellerDto.getSellerPrimaryMoNumber());
        seller.setSellerPrimaryEmail(sellerDto.getSellerPrimaryEmail());
        seller.setSellerSecondaryMoNumber(sellerDto.getSellerSecondaryMoNumber());
        seller.setSellerSecondaryEmail(sellerDto.getSellerSecondaryEmail());
        Seller updatedSellerDetails = sellerRepository.save(seller);
        return updatedSellerDetails;
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


}
