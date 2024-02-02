package com.chargiePizza.pizzaOrder.User.service;

import com.chargiePizza.pizzaOrder.dtos.request.*;

import java.math.BigDecimal;

public interface CustomerService {
    void registerCustomer(CustomerRegisterUserRequest registerUserRequest);

    void unlock(LogInRequest loginRequest);

    String menu(String request);

    void addOrderProduct(OrderProductRequest orderProduct);

    void removeOrder(RemoveOrderRequest orderRequest);

    BigDecimal payment(PaymentRequest paymentRequest);
}
