package com.chargiePizza.pizzaOrder.data.repositories;

import com.chargiePizza.pizzaOrder.data.models.OrderMenu;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderMenuRepository extends MongoRepository<OrderMenu, String> {

}
