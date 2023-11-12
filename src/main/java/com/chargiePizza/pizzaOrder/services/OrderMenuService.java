package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.dtos.UpdateOrderRequest;

public interface OrderMenuService {
    void addOrder(OrderMenu order);

    void removeOrder(OrderMenu order);

    void updateOrder(UpdateOrderRequest updateOrderRequest);

}
