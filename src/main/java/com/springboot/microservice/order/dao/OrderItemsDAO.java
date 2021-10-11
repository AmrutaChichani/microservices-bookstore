package com.springboot.microservice.order.dao;

import com.springboot.microservice.order.entity.OrderItems;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemsDAO  extends CrudRepository<OrderItems, Integer> {
}
