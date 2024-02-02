package com.chargiePizza.pizzaOrder.User.service;


import com.chargiePizza.pizzaOrder.User.data.models.Customer;
import com.chargiePizza.pizzaOrder.User.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.User.data.models.Payment;
import com.chargiePizza.pizzaOrder.dtos.request.*;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.User.data.repositories.CustomerRepository;
import com.chargiePizza.pizzaOrder.expections.*;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.services.PizzaMenuService;
import com.chargiePizza.pizzaOrder.pizzaRestaurant.services.PizzaRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.chargiePizza.pizzaOrder.utils.mapper.map;
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private CustomerRepository customerRepository;
    private PizzaRestaurantService pizzaRestaurantService;
    private OrderMenuService orderMenuService;
    private PaymentService  paymentService;
    private PizzaMenuService pizzaMenuService;

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

        Customer newCustomer = customerOptional.get();
        if (newCustomer.getCustomerPassword().equals(loginRequest.getPassword())) {
            newCustomer.setLock(false);
        } else {
            throw new InvalidPasswordException("Incorrect username and password ");
        }


    }

    @Override
    public String menu(String restaurantName) {
        return pizzaRestaurantService.getFullMenu(restaurantName);
    }



    @Override
    public void addOrderProduct(OrderProductRequest orderProduct) {

        OrderMenu orderMenu = map(orderProduct);
        orderMenu.setCustomerName(getCustomer(orderProduct.getCustomerName()));
        PizzaRestaurant pizzaRestaurant = pizzaRestaurantService.getPizzaRestaurant(orderProduct.getPizzaRestaurantName());
        orderMenu.setPizzaRestaurant(pizzaRestaurant);
        System.out.println(orderMenu);

        PizzaMenu pizzaMenu = pizzaMenuService.findPizzaMenuForOrder(orderProduct.getPizzaName(), orderProduct.getPizzaSize());
        orderMenu.setPizzaName(pizzaMenu);
        orderMenuService.addOrder(orderMenu);

        pizzaRestaurantService.receiveOrder(orderMenu);

    }



    @Override
    public void removeOrder(RemoveOrderRequest orderRequest) {

        Customer customer = getCustomer(orderRequest.getCustomerName());
        pizzaRestaurantService.getPizzaRestaurant(orderRequest.getPizzaRestaurantName());
        OrderMenu orderMenu = orderMenuService.findOrderMenu(orderRequest.getOrderName(), customer);
        orderMenuService.removeOrder(orderMenu);

    }




    @Override
    public BigDecimal payment(PaymentRequest paymentRequest) {
        Customer customer = getCustomer(paymentRequest.getCustomer());
        OrderMenu orderMenu = orderMenuService.findOrderMenu(paymentRequest.getCustomer(), customer);

        BigDecimal totalAmount = orderMenuService.calculateTotalAmount(orderMenu);

        Payment payment = new Payment();
        payment.setPaymentId(orderMenu.getOrderName());
        payment.setTotalAmount(totalAmount);
        paymentService.totalAmount(payment);



        return totalAmount;
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
