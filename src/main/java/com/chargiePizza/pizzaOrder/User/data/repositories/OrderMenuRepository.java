package com.chargiePizza.pizzaOrder.User.data.repositories;

import com.chargiePizza.pizzaOrder.User.data.models.Customer;
import com.chargiePizza.pizzaOrder.User.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaRestaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderMenuRepository extends MongoRepository<OrderMenu, String> {

    Optional<OrderMenu> findOrderMenuByOrderNameAndPizzaRestaurant(String orderName, PizzaRestaurant pizzaRestaurant);

    Optional<OrderMenu> findOrderMenuByOrderNameAndCustomerName(String orderName, Customer customer);


}
