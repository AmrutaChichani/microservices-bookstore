package com.springboot.microservice.order.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerResponse {
    @JsonProperty("id")
    private Integer customerId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

}
