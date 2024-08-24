package com.urbanchic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchase_order_address_table")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer indexId;
    private  String street;
    private  String city;
    private  String state;
    private  String zipCode;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "order_id",referencedColumnName = "orderId")
    private  Order order;
}
