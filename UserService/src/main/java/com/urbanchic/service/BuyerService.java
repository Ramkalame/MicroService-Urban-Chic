package com.urbanchic.service;

import com.urbanchic.dto.AddressDto;
import com.urbanchic.dto.BuyerDto;
import com.urbanchic.entity.Buyer;
import com.urbanchic.entity.helper.Address;

import java.util.List;

public interface BuyerService {

    Buyer createBuyer(BuyerDto buyerDto);
    Buyer getBuyerByBuyerId(String buyerId);
    Buyer getBuyerByPhoneNumber(String phoneNumber);
    Buyer getBuyerByEmail(String email);
    Buyer updateBuyerName(String name,String buyerId);
    Buyer updateBuyerGender(String gender,String buyerId);
    Buyer updateBuyerEmail(String email,String buyerId);
    Buyer updateBuyerPhoneNumber(String phoneNumber,String buyerId);
    void  deleteBuyer(String buyerId);
    List<Address> getBuyerAddresses(String buyerId);
    Address createAddress(AddressDto addressDto,String buyerId);
    List<Address>  updateAddress(AddressDto addressDto,String buyerId,String addressId);
    List<Address> deleteAddress(String buyerId,String addressId);


}
