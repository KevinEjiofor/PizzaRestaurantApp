package com.chargiePizza.pizzaOrder.expections;

public class CustomerNotFoundException extends InvalidPasswordException{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
