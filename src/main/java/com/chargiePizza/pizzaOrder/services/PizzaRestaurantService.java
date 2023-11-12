package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.dtos.*;

import java.util.List;

public interface PizzaRestaurantService {
    void registerRestaurant(RegisterUserRequest registerUserRequest);


    void unlock(LogInRequest loginRequest);


    void addPizzaMenu(AddMenuListRequest menuList);

    void removePizzaNameFromMenu(RemovePizzaMenuRequest removePizzaMenuRequest);

    String getFullMenu(String restaurantName);

    void updatePizzaMenu(UpdateMenuRequest updateMenuRequest);

     PizzaRestaurant getPizzaRestaurant(String pizzaRestaurantName);

    void receiveOrder(OrderMenu orderMenu);


    boolean dispatchOrder(OrderMenu order);

    List<OrderMenu> checkOrderMenuList(CheckMenuRequest request);


}
