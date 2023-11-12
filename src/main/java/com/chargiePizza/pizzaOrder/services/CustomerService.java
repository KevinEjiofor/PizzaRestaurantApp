package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.dtos.CustomerRegisterUserRequest;
import com.chargiePizza.pizzaOrder.dtos.LogInRequest;
import com.chargiePizza.pizzaOrder.dtos.OrderProductRequest;

public interface CustomerService {
    void registerCustomer(CustomerRegisterUserRequest registerUserRequest);

    void unlock(LogInRequest loginRequest);

    String menu(String request);

    void addOrderProduct(OrderProductRequest orderProduct);
}
