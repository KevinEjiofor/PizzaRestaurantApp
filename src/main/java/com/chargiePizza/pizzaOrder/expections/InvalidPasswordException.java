package com.chargiePizza.pizzaOrder.expections;

public class InvalidPasswordException extends PizzaRestaurantAlreadyExistsException{
    public InvalidPasswordException(String message){super(message);}
}
