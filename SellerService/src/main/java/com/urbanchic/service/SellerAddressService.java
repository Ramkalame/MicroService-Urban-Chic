package com.urbanchic.service;

import com.urbanchic.dto.SellerAddressDto;
import com.urbanchic.entity.SellerAddress;
import com.urbanchic.event.SellerDocumentAndAddressDeleteEvent;

public interface SellerAddressService {

    SellerAddress addSellerAddress(SellerAddressDto sellerAddressDto);
    SellerAddress getSellerAddressBySellerId(String sellerId);
    SellerAddress updateSellerAddress(String sellerId, SellerAddressDto sellerAddressDto);
    String deleteSellerAddress(SellerDocumentAndAddressDeleteEvent event);
}
