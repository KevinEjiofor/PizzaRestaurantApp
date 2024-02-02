package com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models;

import com.chargiePizza.pizzaOrder.User.data.models.OrderMenu;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Data
public class PizzaRestaurant{
    @Id
    private String id;
    private String restaurantName;
    private String restaurantUserName;
    private String restaurantEmail;
    private String restaurantAddress;
    private Integer restaurantNumber;
    private String restaurantPassword;
    @DBRef
    private List<PizzaMenu> menu;

    @DBRef
    private List<OrderMenu> customerOrderMenu;

    private boolean isLock;
}
