package com.springboot.microservice.order.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="orders")
@SQLDelete(sql="UPDATE orders SET active_flag=0 where order_id=?")
@Where(clause="active_flag=1")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="order_id")
    private Integer orderId;


    @JoinColumn(name="customer_id")
    private Integer customerId;

    @Column(name="total_amt")
    private double totalAmount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="order_date")
    private Date orderDate;

    @Column(name="status")
    private String status;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private List<OrderItems> orderItems;


    @Column(name="active_flag",columnDefinition = "TINYINT default 1", length = 1)
    private Integer activeFlag;

}
