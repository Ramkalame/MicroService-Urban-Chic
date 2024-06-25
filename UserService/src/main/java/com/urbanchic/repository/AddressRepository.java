package com.urbanchic.repository;

import com.urbanchic.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends MongoRepository<Address,String> {

    List<Address> findAllAddressByUserId(String userId);
}
