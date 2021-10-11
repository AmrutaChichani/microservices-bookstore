package com.springboot.microservice.order.service;

import com.springboot.microservice.order.dao.CartDAO;
import com.springboot.microservice.order.dao.CartItemsDAO;
import com.springboot.microservice.order.dto.CartItemsDTO;
import com.springboot.microservice.order.entity.BookResponse;
import com.springboot.microservice.order.entity.Cart;
import com.springboot.microservice.order.entity.CartItems;
import com.springboot.microservice.order.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartItemsServices {

    @Autowired
    private CartItemsDAO cartItemsDao;


    @Autowired
    private CartDAO cartsDAO;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${book.service.url}")
    private String bookService;

    public Integer addToCart(CartItemsDTO cartItemsDto) {
        String url=bookService+cartItemsDto.getBook();

        ResponseEntity<BookResponse> bookResponse= restTemplate.getForEntity(url, BookResponse.class);

        if(HttpStatus.OK.equals(bookResponse.getStatusCode())) {
            BookResponse book = bookResponse.getBody();
            if (book == null) {
                log.info("Invalid book id requested to add in cart" + cartItemsDto.getBook());
                throw new BadRequestException();
            }
            CartItems cartItems = new CartItems();
            cartItems.setId(cartItemsDto.getId());
            cartItems.setQuantity(cartItemsDto.getQuantity());
            cartItems.setBookId(cartItemsDto.getBook());
            cartItems.setTitle(book.getTitle());
            cartItems.setPrice(book.getPrice());

            Optional<Cart> cartOptional = cartsDAO.findById(cartItemsDto.getCart());
            if (!cartOptional.isPresent()) {
                Integer cartId = cartItems.getCart().getCartId();
                log.info("Inactive cart Id :{}", cartId);
                throw new BadRequestException();
            }
                cartItems.setCart(cartOptional.get());
                cartItemsDao.save(cartItems);
                return cartItems.getId();

        }else{
            log.info("Invalid book id provided.");
            throw new BadRequestException();
        }

    }

    public void deleteFromCart(Integer cartItemId) {
        if (!cartItemsDao.existsById(cartItemId)) {
            log.info("Invalid cart item id :{}", cartItemId);
            throw new BadRequestException();
        }
        cartItemsDao.deleteById(cartItemId);
    }

    public List<CartItems> getCartItemsByCartId(Integer cartId) {
        if (cartItemsDao.existsById(cartId)) {
            return cartItemsDao.findAllByCartId(cartId);
        } else {
            log.info("Invalid cart id:{}", cartId);
            throw new BadRequestException();
        }
    }
}

