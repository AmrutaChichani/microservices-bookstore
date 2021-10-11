package com.springboot.microservice.order.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="cart")
@SQLDelete(sql="UPDATE cart SET active_flag=0 where cart_id=?")
@Where(clause="active_flag=1")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer cartId;

   @Column(name="customer_id")
    private Integer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private List<CartItems> cartItems;


    @Column(name = "active_flag", columnDefinition = "tinyint(1) default 1")
    private Integer activeFlag;
}

