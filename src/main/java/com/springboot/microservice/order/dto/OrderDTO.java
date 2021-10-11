package com.springboot.microservice.order.dto;

import com.springboot.microservice.order.entity.Order;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private Integer id;
    private Double totalAmount;
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    private List<OrderItemDTO> orderItems;
    private ShippingAddressResponseDTO shippingAddress;

    public static OrderDTO from(Order order){
        OrderDTO dto=new OrderDTO();
        if(order.getOrderId()!=0)
            dto.setId(order.getOrderId());

        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderDate(order.getOrderDate());

        List<OrderItemDTO> orderItemsList=new ArrayList<>();
        order.getOrderItems().forEach(item->orderItemsList.add(OrderItemDTO.from(item)));
        dto.setOrderItems(orderItemsList);
        return dto;
    }
}
