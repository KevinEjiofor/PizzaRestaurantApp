package com.chargiePizza.pizzaOrder.utils;

import com.chargiePizza.pizzaOrder.User.data.models.Customer;
import com.chargiePizza.pizzaOrder.User.data.models.OrderMenu;

import com.chargiePizza.pizzaOrder.dtos.request.*;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaRestaurant;


public class mapper {
    public static PizzaRestaurant map(RegisterUserRequest userRequest) {
        PizzaRestaurant user = new PizzaRestaurant();
        user.setRestaurantName(userRequest.getUsername());
        user.setRestaurantPassword(userRequest.getPassword());

        return user;
    }

    public static Customer map(CustomerRegisterUserRequest userRequest) {
        Customer customer = new Customer();
        customer.setCustomerName(userRequest.getCustomerName());
        customer.setCustomerEmail(userRequest.getCustomerEmail());
        customer.setCustomerMobileNumber(userRequest.getCustomerMobileNumber());
        customer.setCustomerAddress(userRequest.getCustomerAddress());
        customer.setCustomerUserName(userRequest.getCustomerUserName());
        customer.setCustomerPassword(userRequest.getCustomerPassword());

        return customer;

    }

    public static PizzaMenu map(AddMenuListRequest menuList) {
        PizzaMenu menu = new PizzaMenu();

        menu.setPizzaName(menuList.getPizzaName());
        menu.setPizzaSize(menuList.getPizzaSize());
        menu.setPizzaPrice(menuList.getPizzaAmount());
        menu.setDrinkName(menuList.getDrinkName());
        menu.setDrinkPrice(menuList.getDrinkPrice());

        return menu;
    }

    public static PizzaMenu map(UpdateMenuRequest updateMenuRequest, PizzaMenu existingPizzaMenu) {
        existingPizzaMenu.setPizzaPrice(updateMenuRequest.getNewPizzaAmount());
        existingPizzaMenu.setDrinkPrice(updateMenuRequest.getNewDrinkAmount());
        existingPizzaMenu.setPizzaSize(updateMenuRequest.getNewPizzaSize());


        return existingPizzaMenu;
    }

    public static OrderMenu map(OrderProductRequest orderRequest) {
        OrderMenu orderMenu = new OrderMenu();

        orderMenu.setOrderName(orderRequest.getOrderName());
        orderMenu.setPizzaSize(orderRequest.getPizzaSize());
        orderMenu.setNumberOfPizza(orderRequest.getNumberOfPizza());
        orderMenu.setDrinkName(orderRequest.getDrinks());
        orderMenu.setNumberOfDrinks(orderRequest.getNumberOfDrinks());
        System.out.println(orderMenu);
        return orderMenu;
    }


}
