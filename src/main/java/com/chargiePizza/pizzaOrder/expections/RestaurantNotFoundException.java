package com.chargiePizza.pizzaOrder.expections;

public class RestaurantNotFoundException extends PizzaRestaurantAlreadyExistsException{
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
