package com.urbanchic.repository;

import com.urbanchic.entity.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct,Integer> {

    List<OrderedProduct> findAllByOrderId(String orderId);
}
