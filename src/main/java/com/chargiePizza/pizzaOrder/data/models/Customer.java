package com.chargiePizza.pizzaOrder.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data

public class Customer {

    @Id
    private String customerId;
    private String customerName;
    private String customerMobileNumber;
    private String customerEmail;
    private String customerAddress;
    private String customerUserName;
    private String customerPassword;
    private boolean isLock;


}
