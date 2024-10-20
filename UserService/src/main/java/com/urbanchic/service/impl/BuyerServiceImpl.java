package com.urbanchic.service.impl;

import com.urbanchic.dto.AddressDto;
import com.urbanchic.dto.BuyerDto;
import com.urbanchic.entity.Buyer;
import com.urbanchic.entity.helper.Address;
import com.urbanchic.entity.role.Role;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.BuyerRepository;
import com.urbanchic.repository.CartRepository;
import com.urbanchic.service.BuyerService;
import com.urbanchic.service.CartService;
import com.urbanchic.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;
    private final CartService cartService;
    private final WishlistService wishlistService;


    @Override
    public Buyer createBuyer(BuyerDto buyerDto) {
        Buyer newBuyer = Buyer.builder()
                .buyerId(buyerDto.getBuyerId())
                .firstName(buyerDto.getFirstName())
                .lastName(buyerDto.getLastName())
                .email(buyerDto.getEmail())
                .phoneNumber(buyerDto.getPhoneNumber())
                .role(Role.BUYER.name())
                .addressList(null)
                .isEmailVerified(false)
                .isPhoneNumberVerified(false)
                .build();
        Buyer savedBuyer =  buyerRepository.save(newBuyer);
        cartService.createCart(savedBuyer.getId());
        wishlistService.createWishlist(savedBuyer.getId());
        return savedBuyer;
    }

    @Override
    public Buyer getBuyerByPhoneNumber(String phoneNumber) {
        Buyer existingBuyer = buyerRepository.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        return existingBuyer;
    }

    @Override
    public Buyer getBuyerByEmail(String email) {
        Buyer existingBuyer = buyerRepository.findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        return existingBuyer;
    }

    @Override
    public Buyer updateBuyerDetails(BuyerDto buyerDto, String buyerId) {
        Buyer existingBuyer = buyerRepository.findById(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        existingBuyer.setFirstName(buyerDto.getFirstName());
        existingBuyer.setLastName(buyerDto.getLastName());
        existingBuyer.setEmail(buyerDto.getEmail());
        existingBuyer.setPhoneNumber(buyerDto.getPhoneNumber());
        return buyerRepository.save(existingBuyer);
    }

    @Override
    public void deleteBuyer(String buyerId) {
        Buyer existingBuyer = buyerRepository.findById(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        cartService.deleteBuyerCart(buyerId);
        wishlistService.deleteBuyerWishlist(buyerId);
        buyerRepository.delete(existingBuyer);
    }

    @Override
    public List<Address> getBuyerAddresses(String buyerId) {
        Buyer existingBuyer = buyerRepository.findById(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        if (existingBuyer.getAddressList() == null){
            throw new EntityNotFoundException("Address Not Added. Please Add Address");
        }
        return existingBuyer.getAddressList();
    }

    @Override
    public Address createAddress(AddressDto addressDto, String buyerId) {
        Buyer existingBuyer = buyerRepository.findById(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        Address newAddress = Address.builder()
                .id(UUID.randomUUID().toString().replaceAll("-","").substring(0,5))
                .houseNumber(addressDto.getHouseNumber())
                .streetName(addressDto.getStreetName())
                .landmark(addressDto.getLandmark())
                .locality(addressDto.getLocality())
                .city(addressDto.getCity())
                .district(addressDto.getDistrict())
                .state(addressDto.getState())
                .country("India")
                .pinCode(addressDto.getPinCode())
                .addressLabel(addressDto.getAddressLabel())
                .build();
        if (existingBuyer.getAddressList() == null){
            List<Address> newAddressList = new ArrayList<>();
            newAddressList.add(newAddress);
            existingBuyer.setAddressList(newAddressList);
        }
        else {
            existingBuyer.getAddressList().add(newAddress);
        }
        Buyer updatedBuyer = buyerRepository.save(existingBuyer);
        return updatedBuyer.getAddressList().get(updatedBuyer.getAddressList().size()-1);
    }

    @Override
    public void updateAddress(AddressDto addressDto, String buyerId,String addressId) {
        Buyer existingBuyer = buyerRepository.findById(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        for (Address address :existingBuyer.getAddressList()){
            if (addressId.equals(address.getId())){
                address.setHouseNumber(addressDto.getHouseNumber());
                address.setStreetName(addressDto.getStreetName());
                address.setLandmark(addressDto.getLandmark());
                address.setLocality(addressDto.getLocality());
                address.setCity(addressDto.getCity());
                address.setDistrict(addressDto.getDistrict());
                address.setState(addressDto.getState());
                address.setPinCode(addressDto.getPinCode());
                address.setAddressLabel(addressDto.getAddressLabel());
            }
        }
        Buyer updatedBuyer = buyerRepository.save(existingBuyer);
    }

    @Override
    public void deleteAddress(String buyerId, String addressId) {
        Buyer existingBuyer = buyerRepository.findById(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        Iterator<Address> iterator = existingBuyer.getAddressList().iterator();
        while (iterator.hasNext()){
            Address address = iterator.next();
            if (addressId.equals(address.getId())){
                iterator.remove();
                break;
            }
        }
        Buyer updatedBuyer = buyerRepository.save(existingBuyer);
    }


}
