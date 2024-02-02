package com.chargiePizza.pizzaOrder.pizzaRestaurant.services;

import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.dtos.request.UpdateMenuRequest;


public interface PizzaMenuService {
    void remove(PizzaMenu pizzaMenu);

    void addMenu(PizzaMenu pizzaMenu);

    PizzaMenu findPizzaMenu(String removePizzaMenuRequest, PizzaRestaurant pizzaRestaurant);
    String getFullMenu();
    void updatePizzaMenu(UpdateMenuRequest updateMenuRequest,PizzaMenu updateMenu);


    PizzaMenu findPizzaMenuForOrder(String pizzaName, String pizzaSize);
}
