package com.chargiePizza.pizzaOrder.data.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Payment {
   @Id
   private String paymentId;
   @DBRef
   private String customer;
   @CreatedDate
   private LocalDateTime dateCreated = LocalDateTime.now();
   private BigDecimal amountOfDrink;
   private BigDecimal amountOfPizza;
   private BigDecimal totalAmount;



}
