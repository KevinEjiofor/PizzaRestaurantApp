package com.chargiePizza.pizzaOrder.data.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Payment {
   @Id
   private String paymentId;
   @DBRef
   private List<Customer> customers;
   @CreatedDate
   private LocalDateTime dateCreated = LocalDateTime.now();

}
