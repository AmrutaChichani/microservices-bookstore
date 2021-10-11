package com.springboot.microservice.order.dto;

import com.springboot.microservice.order.entity.Cart;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDTO {
    private Integer id;
    private Integer customerId;
    private List<CartItemsResponseDTO> cartItems;

    public static CartDTO from(Cart cart){
        CartDTO dto=new CartDTO();
        if(cart.getCartId()!=null)
            dto.setId(cart.getCartId());
        if(cart.getCustomer()!=null){
            dto.setCustomerId(cart.getCustomer());
        }
        if(cart.getCartItems()!=null) {
            List<CartItemsResponseDTO> cartItemDtoList = new ArrayList<>();
            cart.getCartItems().forEach(cartItem -> cartItemDtoList.add(CartItemsResponseDTO.from(cartItem)));
            dto.setCartItems(cartItemDtoList);
        }

        return dto;
    }
}
