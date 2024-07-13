package com.urbanchic.controller;

import com.urbanchic.dto.OrderDto;
import com.urbanchic.dto.UpdateOrderStatusDto;
import com.urbanchic.entity.Order;
import com.urbanchic.service.OrderService;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
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

    @PutMapping("/update/status")
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

    @GetMapping("/seller/{sellerId}")
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

    @GetMapping("/buyer/{buyerId}")
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

}
