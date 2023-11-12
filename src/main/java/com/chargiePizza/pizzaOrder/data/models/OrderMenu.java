package com.chargiePizza.pizzaOrder.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "orderMenu")
public class OrderMenu {

    @Id
    private String id;
    private String orderName;
    @DBRef
    private Customer customerName;
    @DBRef
    private PizzaRestaurant pizzaRestaurant;
    private Payment orderId;
    private String pizzaName;
    private String pizzaSize;
    private int numberOfPizza;
    private String drinks;
    private int numberOfDrinks;

    @Override
    public String toString() {
        return "OrderMenu" +
                "customerName: " + customerName +
                "pizzaName: " + pizzaName + '\n' +
                "pizzaSize: " + pizzaSize + '\n' +
                "numberOfPizza: " + numberOfPizza +
                "drinks: " + drinks + '\n' +
                "numberOfDrinks: " + numberOfDrinks +'\n'+
                '\n';
    }
}

