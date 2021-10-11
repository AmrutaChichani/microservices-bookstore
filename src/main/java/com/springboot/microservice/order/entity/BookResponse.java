package com.springboot.microservice.order.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookResponse {
    @JsonProperty("id")
    private Integer bookId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("price")
    private Double price;
}
