package com.chargiePizza.pizzaOrder.dtos.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RemoveMenuListRequest {
    private String pizzaName;
    private String pizzaSize;
    private BigDecimal pizzaAmount;
    private BigDecimal drinkPrice;
    private String drinkName;

}
