package com.springboot.microservice.order.dto;

import com.springboot.microservice.order.entity.ShippingAddress;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ShippingAddressDTO {
    private Integer id;
    private String locality;
    private String city;
    private String state;
    private Integer orderID;

    @Pattern(regexp = "^[0-9]{6}",message = "Enter 6 digit Pin code")
    private String pinCode;



    public static ShippingAddressDTO from(ShippingAddress address){
        ShippingAddressDTO dto=new ShippingAddressDTO();
        if(address.getShippingId()!=null)
            dto.setId(address.getShippingId());
        dto.setPinCode(address.getPinCode());
        dto.setState(address.getState());
        dto.setCity(address.getCity());
        dto.setLocality(address.getLocality());
        dto.setOrderID(address.getOrderId().getOrderId());
        return dto;
    }

}
