package com.chargiePizza.pizzaOrder.User.service;

import com.chargiePizza.pizzaOrder.User.data.models.Customer;
import com.chargiePizza.pizzaOrder.User.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.dtos.request.UpdateOrderRequest;

import java.math.BigDecimal;

public interface OrderMenuService {
    void addOrder(OrderMenu order);

    void removeOrder(OrderMenu order);

    void updateOrder(UpdateOrderRequest updateOrderRequest);

    OrderMenu findOrder(String orderName, PizzaRestaurant pizzaRestaurant);

    OrderMenu findOrderMenu(String orderName, Customer customer);

    BigDecimal calculateTotalAmount(OrderMenu orderMenu);

}
