package com.chargiePizza.pizzaOrder.User.data.repositories;

import com.chargiePizza.pizzaOrder.User.data.models.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment,String> {
}
