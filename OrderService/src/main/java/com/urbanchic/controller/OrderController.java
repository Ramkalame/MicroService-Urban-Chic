package com.urbanchic.controller;

import com.urbanchic.dto.OrderDto;
import com.urbanchic.dto.UpdateOrderStatusDto;
import com.urbanchic.entity.Order;
import com.urbanchic.entity.statusenum.OrderStatus;
import com.urbanchic.service.OrderService;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody @Valid OrderDto orderDto) {
        Order responseData = orderService.addOrder(orderDto);

        ApiResponse<Order> apiResponse = ApiResponse.<Order>builder()
                .data(responseData)
                .message("Order is completed")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PatchMapping("/update/status")
    public ResponseEntity<?> updateOrderStatus(@RequestBody @Valid UpdateOrderStatusDto updateOrderStatusDto) {
        Order responseData = orderService.updateOrderStatus(updateOrderStatusDto);

        ApiResponse<Order> apiResponse = ApiResponse.<Order>builder()
                .data(responseData)
                .message("Order Status is Updated")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/sellers/{sellerId}")
    public ResponseEntity<?> getAllOrdersOfSeller(@PathVariable("sellerId") String sellerId){
        List<Order> responseData = orderService.getAllOrdersOfSeller(sellerId);

        ApiResponse<List<Order>> apiResponse = ApiResponse.<List<Order>>builder()
                .data(responseData)
                .message("All Orders Of Requested Seller")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/buyers/{buyerId}")
    public ResponseEntity<?> getAllOrderOfBuyer(@PathVariable("buyerId")String buyerId){
        List<Order> responseData = orderService.getAllOrdersOfBuyer(buyerId);

        ApiResponse<List<Order>> apiResponse = ApiResponse.<List<Order>>builder()
                .data(responseData)
                .message("All Orders Of Requested Buyer")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderByOrderId(@PathVariable("orderId") String orderId){
        Order responseData = orderService.getOrderByOrderId(orderId);

        ApiResponse<Order> apiResponse = ApiResponse.<Order>builder()
                .data(responseData)
                .message("Requested Order Fetched Successfully")
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .success(true)
                .build();
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders(){
        List<Order> responseData = orderService.getAllOrders();

        ApiResponse<List<Order>> apiResponse = ApiResponse.<List<Order>>builder()
                .data(responseData)
                .message("Requested Orders Fetched Successfully")
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .success(true)
                .build();
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<?> getAllOrderByStatus(@PathVariable("orderStatus")OrderStatus orderStatus){
        List<Order> responseData = orderService.getAllOrdersByStatus(orderStatus);

        ApiResponse<List<Order>> apiResponse = ApiResponse.<List<Order>>builder()
                .data(responseData)
                .message("Requested Status Orders Fetched Successfully")
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .success(true)
                .build();
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
