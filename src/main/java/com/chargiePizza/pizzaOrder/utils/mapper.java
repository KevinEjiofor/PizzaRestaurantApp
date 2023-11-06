package com.chargiePizza.pizzaOrder.utils;

import com.chargiePizza.pizzaOrder.data.models.Customer;
import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.dtos.AddMenuListRequest;
import com.chargiePizza.pizzaOrder.dtos.CustomerRegisterUserRequest;
import com.chargiePizza.pizzaOrder.dtos.RegisterUserRequest;

public class mapper {
    public static PizzaRestaurant map(RegisterUserRequest userRequest){
        PizzaRestaurant user = new PizzaRestaurant();
        user.setRestaurantName(userRequest.getUsername());
        user.setRestaurantPassword(userRequest.getPassword());

        return user;
    }
    public static Customer map(CustomerRegisterUserRequest userRequest){
        Customer customer = new Customer();
        customer.setCustomerName(userRequest.getCustomerName());
        customer.setCustomerEmail(userRequest.getCustomerEmail());
        customer.setCustomerMobileNumber(userRequest.getCustomerMobileNumber());
        customer.setCustomerAddress(userRequest.getCustomerAddress());
        customer.setCustomerUserName(userRequest.getCustomerUserName());
        customer.setCustomerPassword(userRequest.getCustomerPassword());

        return customer;

    }
    public static PizzaMenu map(AddMenuListRequest menuList){
        PizzaMenu menu = new PizzaMenu();
        menu.setPizzaName(menuList.getPizzaName());
        menu.setPizzaSize(menuList.getPizzaSize());
        menu.setPizzaPrice(menuList.getPizzaAmount());
        menu.setDrinkName(menuList.getDrinkName());
        menu.setDrinkPrice(menuList.getDrinkPrice());

        return menu;
    }


}
