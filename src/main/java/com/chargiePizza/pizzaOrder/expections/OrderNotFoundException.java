package com.chargiePizza.pizzaOrder.expections;

public class OrderNotFoundException extends InvalidPasswordException{
    public OrderNotFoundException(String message) {
        super(message);
    }
}
