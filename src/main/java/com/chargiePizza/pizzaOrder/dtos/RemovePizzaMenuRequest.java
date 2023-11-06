package com.chargiePizza.pizzaOrder.dtos;

import lombok.Data;

@Data
public class RemovePizzaMenuRequest {

    private String pizzaMenuName;
    private String restaurantName;
}
