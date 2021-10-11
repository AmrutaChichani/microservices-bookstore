package com.springboot.microservice.order.dto;

import com.springboot.microservice.order.entity.CartItems;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CartItemsDTO {
    private Integer id;

    @NotNull
    private Integer book;

    @Min(value=1)
    private Integer quantity;

    @NotNull
    private Integer cart;


    public static CartItemsDTO from(CartItems cartItems) {
        CartItemsDTO dto=new CartItemsDTO();
        if(cartItems.getId()!=null)
            dto.setId(cartItems.getId());
        dto.setQuantity(cartItems.getQuantity());
        dto.setBook(cartItems.getBookId());
        dto.setCart(cartItems.getCart().getCartId());
        return dto;
    }
}
