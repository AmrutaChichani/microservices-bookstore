package com.springboot.microservice.order.service;

import com.springboot.microservice.order.dao.ShippingAddressDAO;
import com.springboot.microservice.order.entity.ShippingAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ShippingAddressServices {

    @Autowired
    private ShippingAddressDAO shippingAddressDao;

    public Integer addOrUpdateShippingAddress(ShippingAddress shippingAddress) {
        shippingAddressDao.save(shippingAddress);
        return shippingAddress.getShippingId();
    }
    public ShippingAddress findByOrderId(Integer id){
        Optional<ShippingAddress> address=shippingAddressDao.findByOrderId(id);
        if(!address.isPresent()){
            log.info("Shipping address not available");
            return null;
        }
        return address.get();

    }

}
