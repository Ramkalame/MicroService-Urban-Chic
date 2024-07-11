package com.urbanchic.repository;

import com.urbanchic.entity.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist,String> {

    List<Wishlist> findAllByMobileNo(String mobileNo);
}
