package com.chargiePizza.pizzaOrder.dtos.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddMenuListRequest {

    private String pizzaName;
    private String pizzaSize;
    private BigDecimal pizzaAmount;
    private BigDecimal drinkPrice;
    private String drinkName;
    private String pizzaRestaurantName;

}
