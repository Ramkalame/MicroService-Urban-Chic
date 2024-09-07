package com.urbanchic.service;

import com.urbanchic.entity.Seller;
import com.urbanchic.dto.SellerDto;

import java.util.List;


public interface SellerService {

     Seller createSeller(SellerDto sellerDto);
     Seller updateSeller(String sellerId,SellerDto sellerDto);
     Seller getSellerBySellerId(String sellerId);
     Seller getSellerByPrimaryEmail(String primaryEmail);
     String deleteSeller(String sellerId);


}
