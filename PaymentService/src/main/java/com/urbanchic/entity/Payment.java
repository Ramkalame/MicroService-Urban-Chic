package com.urbanchic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer indexId;
    @Column(unique = true,nullable = false)
    private String paymentId;
    @Column(nullable = false)
    private String paymentOrderId;
    @Column(nullable = false)
    private String paymentSignature;
    @Column(nullable = false)
    private String orderId;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private String currency;
    @Column(nullable = false)
    private String paymentStatus;
    @Column(nullable = false)
    private String paymentMethod;
    @CreationTimestamp
    private LocalDateTime createdDate;

}
