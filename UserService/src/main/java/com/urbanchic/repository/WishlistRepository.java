package com.urbanchic.repository;

import com.urbanchic.entity.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist,String> {

    Optional<Wishlist> findByBuyerId(String buyerId);
}
