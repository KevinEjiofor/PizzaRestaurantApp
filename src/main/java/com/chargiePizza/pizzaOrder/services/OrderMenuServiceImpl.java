package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.data.repositories.OrderMenuRepository;

import com.chargiePizza.pizzaOrder.dtos.UpdateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMenuServiceImpl implements OrderMenuService {
    @Autowired
    private OrderMenuRepository orderRepository;
    @Override
    public void addOrder(OrderMenu order) {
        orderRepository.save(order);

    }

    @Override
    public void removeOrder(OrderMenu order) {

    }

    @Override
    public void updateOrder(UpdateOrderRequest updateOrderRequest) {

    }


}
