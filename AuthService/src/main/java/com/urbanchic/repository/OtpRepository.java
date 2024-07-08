package com.urbanchic.repository;

import com.urbanchic.entity.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends MongoRepository<Otp,String> {

    Optional<Otp> findByOtpNumber(String otpNumber);
    Optional<Otp> findByMoNumber(String mobileNo);
}
