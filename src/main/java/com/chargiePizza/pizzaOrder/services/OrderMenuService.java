package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.Customer;
import com.chargiePizza.pizzaOrder.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.dtos.UpdateOrderRequest;

public interface OrderMenuService {
    void addOrder(OrderMenu order);

    void removeOrder(OrderMenu order);

    void updateOrder(UpdateOrderRequest updateOrderRequest);

    OrderMenu findOrder(String orderName, PizzaRestaurant pizzaRestaurant);



    OrderMenu findOrderMenu(String orderName, Customer customer);
}
