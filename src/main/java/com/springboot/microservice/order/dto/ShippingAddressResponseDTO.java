package com.springboot.microservice.order.dto;

import com.springboot.microservice.order.entity.ShippingAddress;
import lombok.Data;

@Data
public class ShippingAddressResponseDTO {
    private Integer id;
    private String locality;
    private String city;
    private String state;
    private String pinCode;


    public static ShippingAddressResponseDTO from(ShippingAddress address){
        ShippingAddressResponseDTO dto=new ShippingAddressResponseDTO();
        if(address.getShippingId()!=null)
            dto.setId(address.getShippingId());
        dto.setPinCode(address.getPinCode());
        dto.setState(address.getState());
        dto.setCity(address.getCity());
        dto.setLocality(address.getLocality());
        return dto;
    }

}
