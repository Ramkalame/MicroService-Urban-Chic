package com.urbanchic.service;

import com.urbanchic.dto.OrderDto;
import com.urbanchic.dto.UpdateOrderStatusDto;
import com.urbanchic.entity.Order;
import com.urbanchic.entity.OrderedProduct;
import com.urbanchic.entity.statusenum.OrderStatus;

import java.util.List;

public interface OrderService {

    Order addOrder(OrderDto orderDto);
    Order updateOrderStatus(UpdateOrderStatusDto updateOrderStatusDto);
    List<Order> getAllOrdersOfSeller(String sellerId);
    List<Order> getAllOrdersOfBuyer(String buyerId);
//    void createPurchasedOrderMessageDetails(Order savedOrder, List<OrderedProduct> savedOrderedProductList);
    Order getOrderByOrderId(String orderId);
    List<Order> getAllOrders();
    List<Order> getAllOrdersByStatus(OrderStatus orderStatus);
}
