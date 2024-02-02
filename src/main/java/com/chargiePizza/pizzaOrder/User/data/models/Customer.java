package com.chargiePizza.pizzaOrder.User.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "customers")
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
