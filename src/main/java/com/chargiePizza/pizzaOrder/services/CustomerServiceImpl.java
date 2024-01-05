package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.Customer;
import com.chargiePizza.pizzaOrder.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.data.repositories.CustomerRepository;
import com.chargiePizza.pizzaOrder.dtos.CustomerRegisterUserRequest;
import com.chargiePizza.pizzaOrder.dtos.LogInRequest;
import com.chargiePizza.pizzaOrder.dtos.OrderProductRequest;
import com.chargiePizza.pizzaOrder.dtos.RemoveOrderRequest;
import com.chargiePizza.pizzaOrder.expections.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.chargiePizza.pizzaOrder.utils.mapper.map;
@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PizzaRestaurantService pizzaRestaurantService;

    @Autowired
    private OrderMenuService orderMenuService;

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
    public String menu(String restaurantName) {
        return pizzaRestaurantService.getFullMenu(restaurantName);
    }

    @Override
    public void addOrderProduct(OrderProductRequest orderProduct) {
        OrderMenu orderMenu  = map(orderProduct);
        orderMenu.setCustomerName(getCustomer(orderProduct.getCustomerName()));
        PizzaRestaurant pizzaRestaurant = pizzaRestaurantService.getPizzaRestaurant(orderProduct.getPizzaRestaurantName());
        orderMenu.setPizzaRestaurant(pizzaRestaurant);
        orderMenuService.addOrder(orderMenu);
        pizzaRestaurantService.receiveOrder(orderMenu);

    }



    @Override
    public void removeOrder(RemoveOrderRequest orderRequest) {

        Customer customer = getCustomer(orderRequest.getCustomerName());
        pizzaRestaurantService.getPizzaRestaurant(orderRequest.getPizzaRestaurantName());
        OrderMenu orderMenu = orderMenuService.findOrderMenu(orderRequest.getOrderName(),customer);
        orderMenuService.removeOrder(orderMenu);

    }


    private Customer getCustomer(String customerName) {
        Optional<Customer> customer =
                customerRepository.findCustomerByCustomerUserName(customerName);
        if(customer.isEmpty()) throw new CustomerNotFoundException("User name not correct");
        return customer.get();
    }


    private void validateUniqueUsername(String customerName) {
        if (customerRepository.findCustomerByCustomerUserName(customerName).isPresent()) {
            throw new UserAlreadyExistException("USERNAME ALREADY EXIST");

        }
    }
}
