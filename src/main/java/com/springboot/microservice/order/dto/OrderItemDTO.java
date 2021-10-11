package com.springboot.microservice.order.dto;

import com.springboot.microservice.order.entity.OrderItems;
import lombok.Data;

@Data
public class OrderItemDTO {
    private Integer id;
    private Integer bookId;
    private Integer quantity;


    public static OrderItemDTO from(OrderItems orderItems){
        OrderItemDTO dto=new OrderItemDTO();
        if(orderItems.getId()!=null)
            dto.setId(orderItems.getId());
        dto.setBookId(orderItems.getBookId());
        dto.setQuantity(orderItems.getQuantity());

        return dto;
    }
}
