package com.springboot.microservice.order.dao;

import com.springboot.microservice.order.entity.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderDAO extends CrudRepository<Order, Integer> {
    @Modifying
    @Query(value="UPDATE orders SET active_flag=false, status='CANCEL' WHERE order_id=?1", nativeQuery = true)
    public Integer cancelOrder(Integer orderId);
}
