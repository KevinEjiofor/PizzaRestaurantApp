package com.chargiePizza.pizzaOrder.pizzaRestaurant.data.repositories;

import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaRestaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRestaurantRepository extends MongoRepository<PizzaRestaurant, String> {
    Optional<PizzaRestaurant> findPizzaRestaurantByRestaurantNameIgnoreCase(String restaurantName);

    Optional<PizzaRestaurant> findPizzaRestaurantByRestaurantName(String username);
    List<PizzaMenu> findPizzaMenuByRestaurantName(String restaurantName);


}
