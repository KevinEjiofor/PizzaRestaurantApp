package com.chargiePizza.pizzaOrder.expections;

public class UserDoesNotExistException extends PizzaRestaurantAlreadyExistsException{
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
