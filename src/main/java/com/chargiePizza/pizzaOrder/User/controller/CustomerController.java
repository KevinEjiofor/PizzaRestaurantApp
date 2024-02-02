package com.chargiePizza.pizzaOrder.User.controller;

import com.chargiePizza.pizzaOrder.dtos.request.CustomerRegisterUserRequest;
import com.chargiePizza.pizzaOrder.dtos.request.LogInRequest;
import com.chargiePizza.pizzaOrder.dtos.request.OrderProductRequest;
import com.chargiePizza.pizzaOrder.dtos.request.RemoveOrderRequest;
import com.chargiePizza.pizzaOrder.expections.CustomerNotFoundException;
import com.chargiePizza.pizzaOrder.expections.RestaurantNotFoundException;
import com.chargiePizza.pizzaOrder.expections.UserAlreadyExistException;
import com.chargiePizza.pizzaOrder.User.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping("customerRegistration")
    public String register(@RequestBody CustomerRegisterUserRequest registerUserRequest){
        try{
            customerService.registerCustomer(registerUserRequest);
            return "Registration was successful";
        }catch (UserAlreadyExistException error){
          return   error.getMessage();
        }

    }
    @PatchMapping("customerLogin")
    public String unlock(@RequestBody LogInRequest loginRequest ){
        try{
            customerService.unlock(loginRequest);
            return "successfully login";

        }catch (UserAlreadyExistException error){
            return error.getMessage();
        }
    }
    @GetMapping("menu")
    public String fullMenu(String restaurantName){
        try{
            customerService.menu(restaurantName);
            return "Restaurant's menu";

        }catch (RestaurantNotFoundException error){
            return error.getMessage();
        }
    }
    @PostMapping("addOrder")
    public String addOrder(OrderProductRequest orderProduct){
        try {
            customerService.addOrderProduct(orderProduct);
            return "Order was added successfully ";
        }catch(CustomerNotFoundException error){
            return error.getMessage();
        }
    }

    @DeleteMapping("removerOrder")
    public String removeOrder(RemoveOrderRequest orderProduct){
        try{
            customerService.removeOrder(orderProduct);
            return "Was deleted successfully";
        }catch (CustomerNotFoundException error){
            return error.getMessage();
        }
    }



}
