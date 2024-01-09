package com.chargiePizza.pizzaOrder.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    @DBRef
    private PizzaMenu pizza;
    private String pizzaSize;
    private int numberOfPizza;
//    private List<PizzaMenu> drinks;
    private int numberOfDrinks;

    @Override
    public String toString() {
        return "OrderMenu" +
                "customerName: " + customerName +
                "pizzaName: " + pizza + '\n' +
                "pizzaSize: " + pizzaSize + '\n' +
                "numberOfPizza: " + numberOfPizza +
                "drinks: " + pizza.getDrinkName() + '\n' +
                "numberOfDrinks: " + numberOfDrinks +'\n'+
                '\n';
    }
}

