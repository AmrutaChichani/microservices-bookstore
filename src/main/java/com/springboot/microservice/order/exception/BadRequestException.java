package com.springboot.microservice.order.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(){
        super("Bad request posted.");
    }
}
