package com.springboot.microservice.order.controller;

import com.springboot.microservice.order.dto.CartItemsDTO;
import com.springboot.microservice.order.service.CartItemsServices;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/cart")
public class CartItemsController {


    @Autowired
    private CartItemsServices cartItemsServices;

    @PostMapping
    public ResponseEntity<Integer> addToCart(@Valid @RequestBody CartItemsDTO cartItem) {
            Integer result = cartItemsServices.addToCart(cartItem);
            return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<Integer> deleteFromCart(@PathVariable("itemId") Integer cartItemId) {
        cartItemsServices.deleteFromCart(cartItemId);
        return ResponseEntity.ok().body(cartItemId);
    }

}
