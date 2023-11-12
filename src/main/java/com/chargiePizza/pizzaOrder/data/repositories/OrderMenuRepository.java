package com.chargiePizza.pizzaOrder.data.repositories;

import com.chargiePizza.pizzaOrder.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderMenuRepository extends MongoRepository<OrderMenu, String> {

    Optional<OrderMenu> findOrderMenuByOrderNameAndPizzaRestaurant(String orderName, PizzaRestaurant pizzaRestaurant);
}
