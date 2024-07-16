package com.urbanchic.service.impl;

import com.urbanchic.client.ProductServiceClient;
import com.urbanchic.client.UserServiceClient;
import com.urbanchic.dto.OrderDto;
import com.urbanchic.dto.OrderedProductDto;
import com.urbanchic.dto.UpdateOrderStatusDto;
import com.urbanchic.emailandsmsdto.PurchasedOrderEmailDto;
import com.urbanchic.entity.Order;
import com.urbanchic.entity.OrderedProduct;
import com.urbanchic.entity.statusenum.OrderStatus;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.external.Product;
import com.urbanchic.external.User;
import com.urbanchic.repository.OrderRepository;
import com.urbanchic.repository.OrderedProductRepository;
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
    private final OrderedProductRepository orderedProductRepository;
    private final UserServiceClient userServiceClient;
    private final ProductServiceClient productServiceClient;
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
        Order savedOrder = orderRepository.save(newOrder);

        List<OrderedProduct> orderedProductList = new ArrayList<>();

        //Get all the product id from the OrderDto and iterate
        for (OrderedProductDto orderedProductDto:orderDto.getProductIdList()){
            OrderedProduct orderedProduct = OrderedProduct.builder()
                    .productId(orderedProductDto.getProductId())
                    .productQuantity(orderedProductDto.getProductQuantity())
                    .orderId(savedOrder.getOrderId())
                    .build();
            orderedProductList.add(orderedProduct);
        }

        List<OrderedProduct> savedOrderedProductList = orderedProductRepository.saveAll(orderedProductList);
        createPurchasedOrderEmailDetails(savedOrder,savedOrderedProductList);
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

    public void createPurchasedOrderEmailDetails(Order savedOrder, List<OrderedProduct> savedOrderedProductList) {
        List<Product> productList = new ArrayList<>();
        for (OrderedProduct orderedProduct : savedOrderedProductList){
            Product product = productServiceClient.getProductById(orderedProduct.getProductId())
                    .getBody()
                    .getData();
            product.setProductQuantity(orderedProduct.getProductQuantity());
            productList.add(product);
        }
        User user = userServiceClient.getUserById(savedOrder.getBuyerId())
                .getBody()
                .getData();

        PurchasedOrderEmailDto purchasedOrderEmailDto = PurchasedOrderEmailDto.builder()
                .orderId(savedOrder.getOrderId())
                .buyerName(user.getFullName())
                .email(user.getEmail())
                .orderedProductList(productList)
                .beforeTaxAmount(512)
                .estimatedTax(92.15)
                .build();
        messageProducer.sendPurchaseOrderMail(purchasedOrderEmailDto);
    }



}
