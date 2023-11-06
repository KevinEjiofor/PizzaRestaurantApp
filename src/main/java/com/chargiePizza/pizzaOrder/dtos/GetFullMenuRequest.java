package com.chargiePizza.pizzaOrder.dtos;

import lombok.Data;

@Data

public class GetFullMenuRequest {
    private String pizzaRestaurantName;
    private String pizzaMenuName;
}
