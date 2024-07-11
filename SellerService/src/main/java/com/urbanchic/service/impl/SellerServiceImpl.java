package com.urbanchic.service.impl;

import com.urbanchic.exception.SellerAlreadyExistException;
import com.urbanchic.exception.SellerNotFoundException;
import com.urbanchic.model.Seller;
import com.urbanchic.model.SellerDocument;
import com.urbanchic.model.sellerEnum.SellerStatus;
import com.urbanchic.modelDTO.SellerDto;
import com.urbanchic.repository.SellerDocumentRepository;
import com.urbanchic.repository.SellerRepository;
import com.urbanchic.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    private final SellerDocumentRepository sellerDocumentRepository;

    @Override
    public Seller createSeller(SellerDto sellerDto) {
        if (sellerRepository.findBySellerPrimaryEmail(sellerDto.getSellerPrimaryEmail()) != null){
            throw new SellerAlreadyExistException("Seller is already present with this email: "+ sellerDto.getSellerPrimaryEmail());
        }

        Seller newSeller = Seller.builder()
                .sellerFullName(sellerDto.getSellerFullName())
                .sellerEmail(sellerDto.getSellerEmail())
                .sellerPrimaryMoNumber(sellerDto.getSellerPrimaryMoNumber())
                .sellerPrimaryEmail(sellerDto.getSellerPrimaryEmail())
                .sellerSecondaryMoNumber(sellerDto.getSellerSecondaryMoNumber())
                .sellerSecondaryEmail(sellerDto.getSellerSecondaryEmail())
                .sellerAddress(sellerDto.getSellerAddress())
                .build();
        return sellerRepository.save(newSeller);
    }

    @Override
    public Seller updateSeller(SellerDto seller) {
        return null;
    }

    @Override
    public Seller getSellerBySellerId(String email) {
        Seller existingSeller = sellerRepository.findBySellerPrimaryEmail(email);
        if(existingSeller == null){
            throw new SellerNotFoundException("seller not found with this email: "+email);
        }
        return existingSeller;
    }

    @Override
    public List<SellerDocument> getListOfPendingSeller() {
        List<SellerDocument> listOfPendingSeller = sellerDocumentRepository.findBySellerStatus(SellerStatus.PENDING_VERIFICATION);
        if(listOfPendingSeller.isEmpty()){
            throw new NoSuchElementException("there is no pending seller available");
        }
        return listOfPendingSeller;
    }


}
