package com.urbanchic.service;

import com.urbanchic.dto.AddressDto;
import com.urbanchic.dto.BuyerDto;
import com.urbanchic.entity.Buyer;
import com.urbanchic.entity.helper.Address;

import java.util.List;

public interface BuyerService {

    Buyer createBuyer(BuyerDto buyerDto);
    Buyer getBuyerByPhoneNumber(String phoneNumber);
    Buyer getBuyerByEmail(String email);
    Buyer updateBuyerDetails(BuyerDto buyerDto,String buyerId);
    void  deleteBuyer(String buyerId);
    List<Address> getBuyerAddresses(String buyerId);
    Address createAddress(AddressDto addressDto,String buyerId);
    void updateAddress(AddressDto addressDto,String buyerId,String addressId);
    void deleteAddress(String buyerId,String addressId);

}
