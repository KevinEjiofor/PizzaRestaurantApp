package com.chargiePizza.pizzaOrder.data.repositories;

import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.dtos.RegisterUserRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRestaurantRepository extends MongoRepository<PizzaRestaurant, String> {
    Optional<PizzaRestaurant> findPizzaRestaurantByRestaurantNameIgnoreCase(String restaurantName);

    Optional<PizzaRestaurant> findPizzaRestaurantByRestaurantName(String username);
    List<PizzaMenu> findPizzaMenuByRestaurantName(String restaurantName);


}
