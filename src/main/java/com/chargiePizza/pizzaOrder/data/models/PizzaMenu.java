package com.chargiePizza.pizzaOrder.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@Document(collection = "pizzaMenu")
public class PizzaMenu {
    @Id
    private String id;
    @Field(name = "pizzaName")
    private String pizzaName;
    private String pizzaSize;
    @Field(name = "drinkName")
    private String drinkName;
    private BigDecimal pizzaPrice;
    private BigDecimal drinkPrice;
    @DBRef
    private PizzaRestaurant pizzaRestaurant;

    @Override
    public String toString() {
        return  "Pizza Menu:" +
                pizzaName + '\n' +
                "Pizza Size :'" + pizzaSize + '\n' +
                "Pizza Price :" + pizzaPrice + '\n' +
                "Drink Name :" + drinkName + '\n' +
                "Drink Price :" + drinkPrice ;
    }
}
