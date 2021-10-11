package com.springboot.microservice.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name="shipping_address")
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="shipping_id")
    private Integer shippingId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="order_id")
    private Order orderId;

    @Column(name="locality")
    private String locality;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Pattern(regexp = "^[0-9]{6}",message = "Enter 6 digit Pin code")
    @Column(name="pincode")
    private String pinCode;

}