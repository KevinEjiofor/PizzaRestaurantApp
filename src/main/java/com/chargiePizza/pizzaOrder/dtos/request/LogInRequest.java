package com.chargiePizza.pizzaOrder.dtos.request;

import lombok.Data;

@Data
public class LogInRequest {
    private  String username;
    private String password;
}
