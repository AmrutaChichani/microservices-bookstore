package com.springboot.microservice.order.dao;

import com.springboot.microservice.order.entity.CartItems;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemsDAO  extends CrudRepository<CartItems, Integer> {

    @Query(value="SELECT * FROM cart_items WHERE cart_id=?1 ", nativeQuery=true)
    public List<CartItems> findAllByCartId(Integer cartId);

}
