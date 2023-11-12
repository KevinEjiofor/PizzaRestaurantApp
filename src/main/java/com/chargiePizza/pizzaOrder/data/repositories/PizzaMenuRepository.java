package com.chargiePizza.pizzaOrder.data.repositories;

import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PizzaMenuRepository extends MongoRepository<PizzaMenu, String> {


    Optional<PizzaMenu> findPizzaMenuByPizzaNameIgnoreCaseAndPizzaRestaurant(String pizzaName, PizzaRestaurant pizzaRestaurant);


    Optional<PizzaMenu> findAllMeanByPizzaNameAndPizzaRestaurant(String pizzaMenuName, PizzaRestaurant pizzaRestaurant);


}


