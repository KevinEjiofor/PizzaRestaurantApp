package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.dtos.UpdateMenuRequest;


public interface PizzaMenuService {
    void remove(PizzaMenu pizzaMenu);

    void addMenu(PizzaMenu pizzaMenu);

    PizzaMenu findPizzaMenu(String removePizzaMenuRequest, PizzaRestaurant pizzaRestaurant);
    String getFullMenu();
    void updatePizzaMenu(UpdateMenuRequest updateMenuRequest,PizzaMenu updateMenu);





}
