package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.dtos.GetFullMenuRequest;
import com.chargiePizza.pizzaOrder.dtos.RemovePizzaMenuRequest;

import java.util.List;
import java.util.Optional;

public interface PizzaMenuService {
    void remove(PizzaMenu pizzaMenu);

    void addMenu(PizzaMenu pizzaMenu);

    PizzaMenu findPizzaMenu(RemovePizzaMenuRequest removePizzaMenuRequest, PizzaRestaurant pizzaRestaurant);



}
