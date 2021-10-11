package com.springboot.microservice.order.dto;

import com.springboot.microservice.order.entity.CartItems;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Data
public class CartItemsResponseDTO {
    private Integer id;

    @NotNull
    private Integer bookId;

    @Min(value=1)
    private Integer quantity;

    private String title;

    private Double price;

    public static CartItemsResponseDTO from(CartItems cartItems) {
        CartItemsResponseDTO dto=new CartItemsResponseDTO();
        if(cartItems.getId()!=null)
            dto.setId(cartItems.getId());
        dto.setQuantity(cartItems.getQuantity());
        dto.setBookId(cartItems.getBookId());
        dto.setPrice(cartItems.getPrice());
        dto.setTitle(cartItems.getTitle());
        return dto;
    }
}
