package com.urbanchic.service.impl;

import com.urbanchic.dto.SellerAddressDto;
import com.urbanchic.entity.SellerAddress;
import com.urbanchic.event.SellerDocumentAndAddressDeleteEvent;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.SellerAddressRepository;
import com.urbanchic.service.SellerAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
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
                new EntityNotFoundException("Company Address Not Found"));
        return existingSellerAddress;
    }

    @Override
    public SellerAddress updateSellerAddress(String sellerId, SellerAddressDto sellerAddressDto) {
        SellerAddress existingSellerAddress = sellerAddressRepository.findBySellerId(sellerId).orElseThrow(() ->
                new EntityNotFoundException("Company Address Not Found"));

        existingSellerAddress.setStreet(sellerAddressDto.getStreet());
        existingSellerAddress.setCity(sellerAddressDto.getCity());
        existingSellerAddress.setState(sellerAddressDto.getState());
        existingSellerAddress.setCountry(sellerAddressDto.getCountry());
        existingSellerAddress.setPostalCode(sellerAddressDto.getPostalCode());
        SellerAddress updatedSellerAddress = sellerAddressRepository.save(existingSellerAddress);
        return updatedSellerAddress;
    }

    @Override
    @Async
    @EventListener
    public String deleteSellerAddress(SellerDocumentAndAddressDeleteEvent event) {
        SellerAddress existingSellerAddress = sellerAddressRepository.findBySellerId(event.getSellerId()).orElseThrow(() ->
                new EntityNotFoundException("Company Address Not Found"));
        sellerAddressRepository.delete(existingSellerAddress);
        return "Seller Id: "+event.getSellerId();
    }
}
