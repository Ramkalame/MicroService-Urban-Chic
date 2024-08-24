package com.urbanchic.service.impl;

import com.urbanchic.dto.OrderDto;
import com.urbanchic.dto.UpdateOrderStatusDto;
import com.urbanchic.entity.Address;
import com.urbanchic.entity.Order;
import com.urbanchic.entity.OrderedProduct;
import com.urbanchic.entity.statusenum.OrderStatus;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.OrderRepository;
import com.urbanchic.service.MessageProducer;
import com.urbanchic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MessageProducer messageProducer;

    @Override
    public Order addOrder(OrderDto orderDto) {
        String orderId = UUID.randomUUID().toString().replace("-","");

        Order newOrder = Order.builder()
                .orderId(orderId)
                .buyerId(orderDto.getBuyerId())
                .sellerId(orderDto.getSellerId())
                .paymentId(orderDto.getPaymentId())
                .orderStatus(OrderStatus.PACKAGING)
                .build();

        Address address = orderDto.getAddress();
        address.setOrder(newOrder);
        List<OrderedProduct> orderedProducts = new ArrayList<>();
        for (OrderedProduct orderedProduct:orderDto.getOrderedProducts()){
            orderedProduct.setOrder(newOrder);
            orderedProducts.add(orderedProduct);
        }
        newOrder.setOrderedProducts(orderedProducts);
        newOrder.setAddress(address);
        Order savedOrder = orderRepository.save(newOrder);
        messageProducer.sendPurchaseOrderId(savedOrder.getOrderId());
        return savedOrder;
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

    @Override
    public Order getOrderByOrderId(String orderId) {
        Order existingOrder = orderRepository.findByOrderId(orderId).orElseThrow(() ->
                 new EntityNotFoundException("Order Not Found."));
        return  existingOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        if(orderList.isEmpty()){
            throw new EntityNotFoundException("No Orders");
        }
        return orderList;
    }

    @Override
    public List<Order> getAllOrdersByStatus(OrderStatus orderStatus) {
        List<Order> orderList = orderRepository.findAllByOrderStatus(orderStatus);
        if(orderList.isEmpty()){
            throw new EntityNotFoundException("No Orders");
        }
        return orderList;
    }


}
