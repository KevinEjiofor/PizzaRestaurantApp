package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.Customer;
import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.data.repositories.CustomerRepository;
import com.chargiePizza.pizzaOrder.data.repositories.PizzaMenuRepository;
import com.chargiePizza.pizzaOrder.data.repositories.PizzaRestaurantRepository;
import com.chargiePizza.pizzaOrder.dtos.CustomerRegisterUserRequest;
import com.chargiePizza.pizzaOrder.dtos.GetFullMenuRequest;
import com.chargiePizza.pizzaOrder.dtos.LogInRequest;
import com.chargiePizza.pizzaOrder.dtos.PizzaRestaurantNameRequest;
import com.chargiePizza.pizzaOrder.expections.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.chargiePizza.pizzaOrder.utils.mapper.map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private PizzaRestaurantRepository pizzaRestaurantRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PizzaRestaurantService pizzaRestaurantService;

    @Override
    public void registerCustomer(CustomerRegisterUserRequest registerUserRequest) {
       validateUniqueUsername(registerUserRequest.getCustomerUserName());

        customerRepository.save(map(registerUserRequest));

    }

    @Override
    public void unlock(LogInRequest loginRequest) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByCustomerUserName(loginRequest.getUsername());
        if (customerOptional.isEmpty()) {
            throw new UserDoesNotExistException("user don't exist, Kindly register an Account");


        }

        Customer newCustomer =  customerOptional.get();
        if (newCustomer.getCustomerPassword().equals(loginRequest.getPassword())) {
            newCustomer.setLock(false);
        }else{
            throw new InvalidPasswordException("Incorrect username and password ");
        }



    }

    @Override
    public List<PizzaMenu> menu(GetFullMenuRequest request) {
        return  pizzaRestaurantService.getFullMenu(request);


    }

//    static List<PizzaMenu> getAll(PizzaMenuRe pizzaMenu) {
//        List<PizzaMenu> fullMenu = pizzaMenu.findAll();
//
//        if (fullMenu.isEmpty()) {
//            throw new MenuNotFoundException("The menu is currently empty.");
//        } else {
//
//            return fullMenu;
//
//        }
//    }


    private void validateRestaurantUniqueName(String restaurantName) {
        if (pizzaRestaurantRepository.findPizzaRestaurantByRestaurantNameIgnoreCase(restaurantName).isPresent()) {
            throw new PizzaRestaurantAlreadyExistsException("Pizza Restaurant Already Exists");

        }
    }

    private void validateUniqueUsername(String customerName) {
        if (customerRepository.findCustomerByCustomerUserName(customerName).isPresent()) {
            throw new UserAlreadyExistException("USERNAME IS ALREADY EXIST");

        }
    }
}
