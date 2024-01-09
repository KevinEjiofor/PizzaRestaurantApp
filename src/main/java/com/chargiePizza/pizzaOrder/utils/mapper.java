package com.chargiePizza.pizzaOrder.utils;

import com.chargiePizza.pizzaOrder.data.models.*;
import com.chargiePizza.pizzaOrder.dtos.*;

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
        orderMenu.setPizzaName(orderRequest.getPizzaName());
        orderMenu.setPizzaSize(orderRequest.getPizzaSize());
        orderMenu.setNumberOfPizza(orderRequest.getNumberOfPizza());
        orderMenu.setDrinks(orderRequest.getDrinks());
        orderMenu.setNumberOfDrinks(orderRequest.getNumberOfDrinks());
        return orderMenu;
    }

//public static Payment map(){
//        Payment payment = new Payment();
//        OrderMenu orderMenu = new OrderMenu();
//        PizzaMenu menu = new PizzaMenu();
//        payment.setPaymentId(orderMenu.getOrderName());
//        payment.setAmountOfDrink(orderMenu.getNumberOfDrinks() * menu.getDrinkPrice());
//        payment.setAmountOfPizza(orderMenu.getNumberOfPizza() * menu.getPizzaPrice());
//
//        payment.setPaymentId(paymentRequest.getPaymentId());
//        payment.setCustomer(paymentRequest.getCustomer());
//        payment.setAmountOfPizza(paymentRequest.getAmountOfPizza());
//        payment.setAmountOfDrink(paymentRequest.getAmountOfDrink());
//        return payment;
//}
//
//}
}
