package com.chargiePizza.pizzaOrder.expections;

public class PizzaRestaurantAlreadyExistsException extends UserAlreadyExistException{

    public PizzaRestaurantAlreadyExistsException(String message) {
        super(message);
    }
}
