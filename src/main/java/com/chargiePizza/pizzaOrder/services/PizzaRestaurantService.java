package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.Order;
import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.dtos.*;

import java.util.List;

public interface PizzaRestaurantService {
    void registerRestaurant(RegisterUserRequest registerUserRequest);


    void unlock(LogInRequest loginRequest);


    void addPizzaMenu(AddMenuListRequest menuList);

    void removePizzaNameFromMenu(RemovePizzaMenuRequest removePizzaMenuRequest);

    List<PizzaMenu> getFullMenu(GetFullMenuRequest request);

    PizzaMenu updatePizzaMenu(AddMenuListRequest menuList);

    void receiveOrder();

    void updateOrder();

    boolean dispatchOrder(Order order);



}
