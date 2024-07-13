package com.urbanchic.service;

import com.urbanchic.dto.OrderDto;
import com.urbanchic.dto.UpdateOrderStatusDto;
import com.urbanchic.entity.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(OrderDto orderDto);
    Order updateOrderStatus(UpdateOrderStatusDto updateOrderStatusDto);
    List<Order> getAllOrdersOfSeller(String sellerId);
    List<Order> getAllOrdersOfBuyer(String buyerId);

}
