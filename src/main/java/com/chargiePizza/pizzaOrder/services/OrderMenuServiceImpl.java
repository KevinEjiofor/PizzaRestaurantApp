package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.Customer;
import com.chargiePizza.pizzaOrder.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.data.repositories.OrderMenuRepository;

import com.chargiePizza.pizzaOrder.dtos.UpdateOrderRequest;
import com.chargiePizza.pizzaOrder.expections.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        orderRepository.delete(order);
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

    @Override
    public OrderMenu findOrderMenu(String orderName, Customer customer) {
        Optional<OrderMenu> orderMenu = orderRepository.
                findOrderMenuByOrderNameAndCustomerName(orderName, customer);
        if(orderMenu.isEmpty()) throw new OrderNotFoundException("Order Not Found");
        return orderMenu.get();
    }
    public BigDecimal calculateTotalAmount(OrderMenu orderMenu) {

        BigDecimal totalAmount = BigDecimal.ZERO;

        totalAmount = totalAmount
                .add(orderMenu.getPizza().getPizzaPrice()
                        .multiply(BigDecimal.valueOf(orderMenu.getNumberOfPizza())));
        totalAmount = totalAmount
                .add(orderMenu.getPizza().getDrinkPrice()
                        .multiply(BigDecimal.valueOf(orderMenu.getNumberOfDrinks())));

        return totalAmount;
    }




}
