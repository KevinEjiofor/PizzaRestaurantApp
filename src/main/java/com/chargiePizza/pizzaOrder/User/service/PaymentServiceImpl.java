package com.chargiePizza.pizzaOrder.User.service;

import com.chargiePizza.pizzaOrder.User.data.models.Payment;
import com.chargiePizza.pizzaOrder.User.data.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
   private PaymentRepository paymentRepository;

    @Override
    public void totalAmount(Payment payment) {
        paymentRepository.save(payment);


    }
}
