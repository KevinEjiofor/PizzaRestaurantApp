package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.dtos.CustomerRegisterUserRequest;
import com.chargiePizza.pizzaOrder.dtos.GetFullMenuRequest;
import com.chargiePizza.pizzaOrder.dtos.LogInRequest;
import com.chargiePizza.pizzaOrder.dtos.PizzaRestaurantNameRequest;

import java.util.List;

public interface CustomerService {
    void registerCustomer(CustomerRegisterUserRequest registerUserRequest);

    void unlock(LogInRequest loginRequest);

    List<PizzaMenu> menu(GetFullMenuRequest request);
}
