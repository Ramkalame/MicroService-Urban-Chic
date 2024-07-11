package com.urbanchic.service;

import com.urbanchic.model.Seller;
import com.urbanchic.model.SellerDocument;
import com.urbanchic.modelDTO.SellerDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SellerService {

    public Seller createSeller(SellerDto sellerDto);
    public Seller updateSeller(SellerDto seller);
    public Seller getSellerBySellerId(String email);
    public List<SellerDocument> getListOfPendingSeller();


}
