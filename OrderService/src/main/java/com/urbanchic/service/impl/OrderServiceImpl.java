package com.urbanchic.service.impl;

import com.urbanchic.dto.OrderDto;
import com.urbanchic.dto.UpdateOrderStatusDto;
import com.urbanchic.entity.Order;
import com.urbanchic.entity.statusenum.OrderStatus;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.OrderRepository;
import com.urbanchic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    public Order addOrder(OrderDto orderDto) {
        Order newOrder = Order.builder()
                .orderId(UUID.randomUUID().toString().replace("-",""))
                .productId(orderDto.getProductId())
                .buyerId(orderDto.getBuyerId())
                .sellerId(orderDto.getSellerId())
                .paymentId(orderDto.getPaymentId())
                .orderStatus(OrderStatus.PACKAGING)
                .build();
        return orderRepository.save(newOrder);
    }

    @Override
    public Order updateOrderStatus(UpdateOrderStatusDto updateOrderStatusDto) {
        Order existingOrder = orderRepository.findByOrderId(updateOrderStatusDto.getOrderId()).orElseThrow(()->
                new EntityNotFoundException("Order Not Found"));
        for (OrderStatus status :OrderStatus.values()){
            if (status.name().equals(updateOrderStatusDto.getOrderStatus())){
                existingOrder.setOrderStatus(status);
                break;
            }
        }
        return  orderRepository.save(existingOrder);
    }

    @Override
    public List<Order> getAllOrdersOfSeller(String sellerId) {
        List<Order> orderList = orderRepository.findAllBySellerId(sellerId);
        if (orderList.isEmpty()){
            throw new EntityNotFoundException("Empty : No Orders");
        }
        return orderList;
    }

    @Override
    public List<Order> getAllOrdersOfBuyer(String buyerId) {
        List<Order> orderList = orderRepository.findAllByBuyerId(buyerId);
        if (orderList.isEmpty()){
            throw new EntityNotFoundException("Empty : No Orders");
        }
        return orderList;
    }
}
