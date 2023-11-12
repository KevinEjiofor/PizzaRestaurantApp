package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.dtos.*;

public interface PizzaRestaurantService {
    void registerRestaurant(RegisterUserRequest registerUserRequest);


    void unlock(LogInRequest loginRequest);


    void addPizzaMenu(AddMenuListRequest menuList);

    void removePizzaNameFromMenu(RemovePizzaMenuRequest removePizzaMenuRequest);

    String getFullMenu(String restaurantName);

    void updatePizzaMenu(UpdateMenuRequest updateMenuRequest);



    void receiveOrder(OrderMenu orderMenu);


    boolean dispatchOrder(OrderMenu order);



}
