package com.urbanchic.service;

import com.urbanchic.dto.SellerAddressDto;
import com.urbanchic.entity.SellerAddress;

public interface SellerAddressService {

    SellerAddress addSellerAddress(SellerAddressDto sellerAddressDto);
    SellerAddress getSellerAddressBySellerId(String sellerId);
    SellerAddress updateSellerAddress(String id,SellerAddressDto sellerAddressDto);
}
