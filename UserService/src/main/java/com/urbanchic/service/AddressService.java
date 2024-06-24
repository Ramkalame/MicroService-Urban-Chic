package com.urbanchic.service;

import com.urbanchic.entity.Address;

import java.util.List;

public interface AddressService {

    Address createAddress(Address address);

    List<Address> getAllAddressByUserId(String userId);

    Address updateAddress(Address address);

    String deleteAddress(String userId, String addressId);
}
