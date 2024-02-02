package com.chargiePizza.pizzaOrder.dtos.request;

import lombok.Data;

@Data
public class OrderProductRequest {
    private String orderName;
    private String customerName;
    private String pizzaName;
    private String pizzaSize;
    private int numberOfPizza;
    private String drinks;
    private int numberOfDrinks;
    private String pizzaRestaurantName;
}
