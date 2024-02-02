package com.chargiePizza.pizzaOrder.User.data.repositories;
import com.chargiePizza.pizzaOrder.User.data.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {


    Optional<Customer> findCustomerByCustomerUserName(String customerUserName);
}
