package com.chargiePizza.pizzaOrder.dtos;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class UpdateMenuRequest {
    private String pizzaRestaurantName;
    private String drinkName;
    private String pizzaName;
    private BigDecimal newPizzaAmount;
    private String newPizzaSize;
    private BigDecimal newDrinkAmount;

}
