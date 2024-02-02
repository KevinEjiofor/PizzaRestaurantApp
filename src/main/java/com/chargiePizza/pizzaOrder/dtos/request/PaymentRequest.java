package com.chargiePizza.pizzaOrder.dtos.request;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {

    private String customer;
    private String paymentId;
    private BigDecimal amountOfDrink;
    private BigDecimal amountOfPizza;
    private BigDecimal totalAmount;



}
