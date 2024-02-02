package com.chargiePizza.pizzaOrder.dtos.request;

import lombok.Data;
import javax.validation.constraints.Email;

@Data
public class CustomerRegisterUserRequest {
    private String customerName;
    private String customerMobileNumber;
    @Email
    private String customerEmail;
    private String customerAddress;
    private String customerUserName;
    private String customerPassword;

}
