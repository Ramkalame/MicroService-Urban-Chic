package com.urbanchic.service.impl;

import com.urbanchic.dto.SellerAddressDto;
import com.urbanchic.entity.SellerAddress;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.SellerAddressRepository;
import com.urbanchic.service.SellerAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerAddressServiceImpl implements SellerAddressService {

    private final SellerAddressRepository sellerAddressRepository;

    @Override
    public SellerAddress addSellerAddress(SellerAddressDto sellerAddressDto) {
        SellerAddress sellerAddress = SellerAddress.builder()
                .street(sellerAddressDto.getStreet())
                .city(sellerAddressDto.getCity())
                .state(sellerAddressDto.getState())
                .country(sellerAddressDto.getCountry())
                .sellerId(sellerAddressDto.getSellerId())
                .build();
        SellerAddress savedSellerAddress = sellerAddressRepository.save(sellerAddress);
        return savedSellerAddress;
    }

    @Override
    public SellerAddress getSellerAddressBySellerId(String sellerId) {
        SellerAddress existingSellerAddress = sellerAddressRepository.findBySellerId(sellerId).orElseThrow(() ->
                new EntityNotFoundException("Seller Address Not Found"));
        return existingSellerAddress;
    }

    @Override
    public SellerAddress updateSellerAddress(String id, SellerAddressDto sellerAddressDto) {
        SellerAddress existingSellerAddress = sellerAddressRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Seller Address Not Found"));

        existingSellerAddress.setStreet(sellerAddressDto.getStreet());
        existingSellerAddress.setCity(sellerAddressDto.getCity());
        existingSellerAddress.setState(sellerAddressDto.getState());
        existingSellerAddress.setCountry(sellerAddressDto.getCountry());
        existingSellerAddress.setPostalCode(sellerAddressDto.getPostalCode());
        SellerAddress updatedSellerAddress = sellerAddressRepository.save(existingSellerAddress);
        return updatedSellerAddress;
    }
}
