package com.chargiePizza.pizzaOrder.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {

    @Id
    private String orderId;
    private boolean orderPayment;
    private  BigDecimal orderCost;
    private Date orderDate;


}
