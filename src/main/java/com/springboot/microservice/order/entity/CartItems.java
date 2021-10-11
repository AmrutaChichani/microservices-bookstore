package com.springboot.microservice.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="cart_items")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name="book_id")
    private Integer bookId;

    @Column(name="book_name")
    private String title;

    @Column(name="price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

}
