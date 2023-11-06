package com.chargiePizza.pizzaOrder.expections;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {super(message);}
}
