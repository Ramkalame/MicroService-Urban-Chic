package com.urbanchic.entity;

import com.urbanchic.entity.statusenum.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchase_order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer indexId;
    private String orderId;
    private String productId;
    private String buyerId;
    private String sellerId;
    private Integer paymentId;
    private OrderStatus orderStatus;

}
