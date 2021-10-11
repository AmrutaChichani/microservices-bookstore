package com.springboot.microservice.order.service;

import com.springboot.microservice.order.dao.OrderItemsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsServices {
    @Autowired
    private OrderItemsDAO orderItemsDao;
}
