package com.springboot.microservice.order.controller;

import com.springboot.microservice.order.dto.CartDTO;
import com.springboot.microservice.order.service.CartServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/cart")
public class CartController {

    @Autowired
    private CartServices cartServices;

    @PostMapping("/{customerId}")
    public ResponseEntity<Integer> createOrGetCart(@RequestHeader("Authorization") String token, @PathVariable("customerId") Integer customerId) {

        Integer result= cartServices.getCarts(token,customerId);
        return ResponseEntity.ok().body(result);

    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCartByID(@PathVariable("cartId") Integer cartId) {
        CartDTO result=CartDTO.from(cartServices.getCartsById(cartId));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Integer> deleteCartByID(@PathVariable("cartId") Integer cartID) {
        cartServices.deleteCartById(cartID);
        return ResponseEntity.ok().body(cartID);

    }
}
