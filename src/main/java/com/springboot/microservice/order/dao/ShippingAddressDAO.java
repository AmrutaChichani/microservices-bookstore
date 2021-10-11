package com.springboot.microservice.order.dao;

import com.springboot.microservice.order.entity.ShippingAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShippingAddressDAO extends CrudRepository<ShippingAddress, Integer> {
    @Query(value="SELECT * FROM shipping_address WHERE order_id=?1", nativeQuery = true)
    Optional<ShippingAddress> findByOrderId(Integer id);
}
