package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.data.repositories.OrderMenuRepository;

import com.chargiePizza.pizzaOrder.dtos.UpdateOrderRequest;
import com.chargiePizza.pizzaOrder.expections.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public OrderMenu findOrder(String orderName, PizzaRestaurant pizzaRestaurant) {
        Optional<OrderMenu> orderMenu =  orderRepository
                .findOrderMenuByOrderNameAndPizzaRestaurant(orderName, pizzaRestaurant);
        if(orderMenu.isEmpty()) throw new OrderNotFoundException("Order Not Found");
        return orderMenu.get();
    }


}
