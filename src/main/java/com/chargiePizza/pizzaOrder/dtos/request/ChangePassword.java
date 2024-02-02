package com.chargiePizza.pizzaOrder.dtos.request;

import lombok.Data;

@Data
public class ChangePassword {
    private  String username;
    private String oldPassword;
    private String newPassword;
}
