package com.springboot.microservice.order.service;

import com.springboot.microservice.order.dao.CartDAO;
import com.springboot.microservice.order.entity.Cart;
import com.springboot.microservice.order.entity.CustomerResponse;
import com.springboot.microservice.order.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class CartServices {

    @Autowired
    private CartDAO cartsDao;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${customer.service.url}")
    private String customerService;

    public Integer getCarts(String token,Integer customerId) {
        log.info("Url:{}",customerService);
        String url=customerService+customerId;
        HttpHeaders header=new HttpHeaders();
        header.set("Authorization",token);

        HttpEntity<HttpHeaders> entity=new HttpEntity<>(header);

        ResponseEntity<CustomerResponse> result=restTemplate.exchange(url, HttpMethod.GET,entity,CustomerResponse.class);

        if(!HttpStatus.OK.equals(result.getStatusCode())){
            log.info("Invalid customer id.");
            throw new BadRequestException();
        }

            Integer activeCart = cartsDao.findByCustomerId(customerId);
            if (activeCart == null) {
                Cart newCart = new Cart();
                newCart.setCustomer(customerId);
                newCart.setActiveFlag(1);
                cartsDao.save(newCart);
                activeCart = cartsDao.findByCustomerId(customerId);
            }
            return(activeCart);

    }

    public Cart getCartsById(Integer cartId) {

        Optional<Cart> cartOptional = cartsDao.findById(cartId);
        if(!cartOptional.isPresent()) {
            log.info("Invalid Cart Id {}", cartId);
            throw new BadRequestException();
        }
        return cartOptional.get();
    }

    public void deleteCartById(Integer cartId)
    {
        if(cartsDao.existsById(cartId)) {
            cartsDao.deleteById(cartId);
        }else{
            log.info("Invalid cart Id :{}",cartId);
            throw new BadRequestException();
        }
    }
    public void updateCart(Cart cart)
    {
        cartsDao.save(cart);
    }
}
