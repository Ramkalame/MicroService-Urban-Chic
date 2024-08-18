package com.urbanchic.repository;

import com.urbanchic.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllBySellerId(String sellerId);
    List<Order> findAllByBuyerId(String buyerId);
    Optional<Order> findByOrderId(String orderId);

}
