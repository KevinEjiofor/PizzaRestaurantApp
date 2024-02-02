package com.chargiePizza.pizzaOrder.pizzaRestaurant.data.repositories;

import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaRestaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PizzaMenuRepository extends MongoRepository<PizzaMenu, String> {


    Optional<PizzaMenu> findPizzaMenuByPizzaNameIgnoreCaseAndPizzaRestaurant(String pizzaName, PizzaRestaurant pizzaRestaurant);


    Optional<PizzaMenu> findAllMeanByPizzaNameAndPizzaRestaurant(String pizzaMenuName, PizzaRestaurant pizzaRestaurant);


    Optional<PizzaMenu> findPizzaMenuByPizzaNameIgnoreCaseAndPizzaSize(String pizza, String pizzaSize);

}


