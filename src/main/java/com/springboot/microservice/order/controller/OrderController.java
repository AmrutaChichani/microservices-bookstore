package com.springboot.microservice.order.controller;

import com.springboot.microservice.order.dto.OrderDTO;
import com.springboot.microservice.order.dto.ShippingAddressDTO;
import com.springboot.microservice.order.service.OrderServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    private OrderServices orderService;

    @PostMapping("/{cartId}")
    public ResponseEntity<Integer> placeOrder(@PathVariable("cartId") Integer cartId) {
        log.debug("Inside OrderController");
        log.info("Placing order for cart id: {}",cartId);
        Integer result=orderService.placeOrder(cartId);
        return ResponseEntity.ok().body(result);
    }
    @PostMapping("/address")
    public ResponseEntity<Integer> mapShippingAddress(@Valid @RequestBody ShippingAddressDTO shippingAddress) {

        Integer result=orderService.addOrUpdateShippingAddress(shippingAddress);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> viewOrder(@PathVariable("orderId") Integer orderId) {
        OrderDTO result= orderService.viewOrderById(orderId);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Integer> cancelOrder(@PathVariable("orderId") Integer orderId) {
        Integer result=orderService.cancelOrder(orderId);
        return ResponseEntity.ok().body(result);
    }
}
