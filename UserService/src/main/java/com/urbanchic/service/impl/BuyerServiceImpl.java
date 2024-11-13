package com.urbanchic.service.impl;

import com.urbanchic.dto.AddressDto;
import com.urbanchic.dto.BuyerDto;
import com.urbanchic.entity.Buyer;
import com.urbanchic.entity.helper.Address;
import com.urbanchic.entity.role.Gender;
import com.urbanchic.entity.role.Role;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.BuyerRepository;
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
                .name(buyerDto.getName())
                .gender(buyerDto.getGender())
                .email(buyerDto.getEmail())
                .phoneNumber(buyerDto.getPhoneNumber())
                .role(Role.BUYER.name())
                .addressList(null)
                .isEmailVerified(false)
                .isPhoneNumberVerified(true)
                .build();
        Buyer savedBuyer =  buyerRepository.save(newBuyer);
        cartService.createCart(savedBuyer.getId());
        wishlistService.createWishlist(savedBuyer.getId());
        return savedBuyer;
    }

    @Override
    public Buyer getBuyerByBuyerId(String buyerId) {
        Buyer existingBuyer = buyerRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        return existingBuyer;
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
    public Buyer updateBuyerName(String name, String buyerId) {
        Buyer existingBuyer = buyerRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        existingBuyer.setName(name);
        return buyerRepository.save(existingBuyer);
    }

    @Override
    public Buyer updateBuyerGender(String gender, String buyerId) {
        Buyer existingBuyer = buyerRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        for (Gender genderValue: Gender.values()){
                if (gender.equals(genderValue.name())){
                    existingBuyer.setGender(genderValue.name());
                }
        }
        return buyerRepository.save(existingBuyer);
    }

    @Override
    public Buyer updateBuyerEmail(String email, String buyerId) {
        Buyer existingBuyer = buyerRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        existingBuyer.setEmail(email);
        return buyerRepository.save(existingBuyer);
    }

    @Override
    public Buyer updateBuyerPhoneNumber(String phoneNumber, String buyerId) {
        Buyer existingBuyer = buyerRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        existingBuyer.setPhoneNumber(phoneNumber);
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
        Buyer existingBuyer = buyerRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        if (existingBuyer.getAddressList() == null){
            throw new EntityNotFoundException("Address Not Added. Please Add Address");
        }
        return existingBuyer.getAddressList();
    }

    @Override
    public Address createAddress(AddressDto addressDto, String buyerId) {
        Buyer existingBuyer = buyerRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        Address newAddress = Address.builder()
                .id(UUID.randomUUID().toString().replaceAll("-","").substring(0,5))
                .houseNumber(addressDto.getHouseNumber())
                .streetName(addressDto.getStreetName())
                .landmark(addressDto.getLandmark())
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
    public List<Address>  updateAddress(AddressDto addressDto, String buyerId,String addressId) {
        Buyer existingBuyer = buyerRepository.findByBuyerId(buyerId).orElseThrow(() ->
                new EntityNotFoundException("User Not Found!! Please Register"));
        for (Address address :existingBuyer.getAddressList()){
            if (addressId.equals(address.getId())){
                address.setHouseNumber(addressDto.getHouseNumber());
                address.setStreetName(addressDto.getStreetName());
                address.setLandmark(addressDto.getLandmark());
                address.setCity(addressDto.getCity());
                address.setDistrict(addressDto.getDistrict());
                address.setState(addressDto.getState());
                address.setPinCode(addressDto.getPinCode());
                address.setAddressLabel(addressDto.getAddressLabel());
            }
        }
        Buyer updatedBuyer = buyerRepository.save(existingBuyer);
         return updatedBuyer.getAddressList();
    }

    @Override
    public List<Address> deleteAddress(String buyerId, String addressId) {
        Buyer existingBuyer = buyerRepository.findByBuyerId(buyerId).orElseThrow(() ->
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
        return updatedBuyer.getAddressList();
    }


}
