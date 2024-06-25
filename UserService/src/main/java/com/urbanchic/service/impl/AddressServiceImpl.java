package com.urbanchic.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.urbanchic.entity.Address;
import com.urbanchic.exception.AddressNotFoundException;
import com.urbanchic.repository.AddressRepository;
import com.urbanchic.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address) {
        Address newAddress = Address.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .userId(address.getUserId())
                .build();
        return addressRepository.save(newAddress);
    }

    @Override
    public List<Address> getAllAddressByUserId(String userId) {
        List<Address> addressList = addressRepository.findAllAddressByUserId(userId);
        if (addressList.isEmpty()) {
            throw new NoSuchElementException("There is no address associated with this user");
        }
        return addressList;
    }

    @Override
    public Address updateAddress(Address updateAddress) {
        Address existingAddress = addressRepository.findById(updateAddress.getAddressId()).orElseThrow(() ->
                new AddressNotFoundException("NO address exists with this id"));
        existingAddress.setStreet(updateAddress.getStreet());
        existingAddress.setCity(updateAddress.getCity());
        existingAddress.setState(updateAddress.getState());
        existingAddress.setZipCode(updateAddress.getZipCode());
        return addressRepository.save(existingAddress);
    }

    @Override
    public String deleteAddress(String addressId) {
            Address existingAddress = addressRepository.findById(addressId).orElseThrow(() ->
                    new AddressNotFoundException("NO address exists with this id"));
        addressRepository.deleteById(addressId);
        return "Deleted address with id "+addressId+" successfully";
    }
}
