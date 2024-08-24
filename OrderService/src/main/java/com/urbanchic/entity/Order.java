package com.urbanchic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urbanchic.entity.statusenum.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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
    @Column(unique = true,nullable = false)
    private String orderId;
    @Column(nullable = false)
    private String buyerId;
    @Column(nullable = false)
    private String sellerId;
    @Column(unique = true,nullable = false)
    private String paymentId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedData;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderedProduct> orderedProducts;

    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    private Address address;

}
