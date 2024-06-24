package com.urbanchic.repository;

import com.urbanchic.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<User,String> {

   Optional<User> findUserByEmail(String email);
}
