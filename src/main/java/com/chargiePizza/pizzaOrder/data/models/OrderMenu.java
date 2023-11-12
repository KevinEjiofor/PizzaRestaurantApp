package com.chargiePizza.pizzaOrder.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class OrderMenu {

    @Id
    private String id;
    @DBRef
    private Customer customerName;
    private Payment orderId;
    private String pizzaName;
    private String pizzaSize;
    private int numberOfPizza;
    private String drinks;
    private int numberOfDrinks;


}
