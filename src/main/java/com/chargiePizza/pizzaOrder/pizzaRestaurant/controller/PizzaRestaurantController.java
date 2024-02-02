package com.chargiePizza.pizzaOrder.pizzaRestaurant.controller;

import com.chargiePizza.pizzaOrder.User.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.dtos.request.*;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.expections.InvalidPasswordException;

import com.chargiePizza.pizzaOrder.expections.MenuNotFoundException;
import com.chargiePizza.pizzaOrder.expections.PizzaRestaurantAlreadyExistsException;
import com.chargiePizza.pizzaOrder.expections.RestaurantNotFoundException;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.services.PizzaRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class PizzaRestaurantController {
    @Autowired
    private PizzaRestaurantService pizzaRestaurantService;


    @PostMapping("/register")
    public String register(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            pizzaRestaurantService.registerRestaurant(registerUserRequest);
            return "Successful";
        } catch (RestaurantNotFoundException error) {
            return error.getMessage();

        }
    }

    @PatchMapping("/login")
    public String unLock(@RequestBody LogInRequest loginRequest) {
        try {
            pizzaRestaurantService.unlock(loginRequest);
            return "Pizza Restaurant Unlocked";
        } catch (InvalidPasswordException error) {
            return error.getMessage();
        }
    }

    @PostMapping("/createMenu")
    public String createMenu(@RequestBody AddMenuListRequest menuListRequest) {
        try {
            pizzaRestaurantService.addPizzaMenu(menuListRequest);
            return "Pizza Menu created successfully";
        } catch (RestaurantNotFoundException error) {
            return error.getMessage();

        }
    }
    @PutMapping("/updateMenu")
    public String updateMenu(@RequestBody UpdateMenuRequest updateMenuRequest ){
        try{
            pizzaRestaurantService.updatePizzaMenu(updateMenuRequest);
            return "Pizza Menu updated successfully";

        }catch(MenuNotFoundException error){
            return error.getMessage();
        }
    }


    @DeleteMapping("/removeMenu")
    public String removeFromMenu(@RequestBody RemovePizzaMenuRequest removePizzaMenuRequest) {
        try {
            pizzaRestaurantService.removePizzaNameFromMenu(removePizzaMenuRequest);
            return "pizza deleted successfully";

        } catch (PizzaRestaurantAlreadyExistsException error) {
            return error.getMessage();
        }
    }

    @GetMapping("/get-menu")
    public String getFullMenu(@RequestParam String restaurantName){
        try {
           return pizzaRestaurantService.getFullMenu(restaurantName);
        }catch (MenuNotFoundException error){
            return error.getMessage();
        }

    }

    @PostMapping("/receiveOrder")
    public String receiveOrder(OrderMenu orderMenu) {
        try {
            pizzaRestaurantService.receiveOrder(orderMenu);
            return "Order received successfully";

        } catch (MenuNotFoundException error) {
           return error.getMessage();

        }

    }

    @GetMapping("/orderList")
    public String getOrderList(CheckMenuRequest request){
        try {
            PizzaRestaurant pizzaRestaurant = pizzaRestaurantService.getPizzaRestaurant(request.getPiazzaRestaurant());
            return pizzaRestaurant.getCustomerOrderMenu().toString();

        } catch (Exception error){
           return error.getMessage();
        }

    }

}