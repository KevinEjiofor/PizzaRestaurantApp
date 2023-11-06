package com.chargiePizza.pizzaOrder.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.chargiePizza.pizzaOrder.data.models.Payment;

public interface PaymentRepository extends MongoRepository<Payment ,String> {
}
