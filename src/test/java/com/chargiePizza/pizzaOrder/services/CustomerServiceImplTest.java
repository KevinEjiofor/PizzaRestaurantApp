package com.chargiePizza.pizzaOrder.services;



import java.math.BigDecimal;
import java.util.List;

import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.repositories.OrderMenuRepository;
import com.chargiePizza.pizzaOrder.data.repositories.PizzaMenuRepository;
import com.chargiePizza.pizzaOrder.data.repositories.PizzaRestaurantRepository;
import com.chargiePizza.pizzaOrder.dtos.*;
import com.chargiePizza.pizzaOrder.expections.MenuNotFoundException;
import org.mockito.Mockito;



import com.chargiePizza.pizzaOrder.data.models.Customer;
import com.chargiePizza.pizzaOrder.data.repositories.CustomerRepository;
import com.chargiePizza.pizzaOrder.expections.InvalidPasswordException;
import com.chargiePizza.pizzaOrder.expections.UserAlreadyExistException;
import com.chargiePizza.pizzaOrder.expections.UserDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private PizzaRestaurantService pizzaRestaurantService;
    @Autowired
    private PizzaRestaurantRepository pizzaRestaurantRepository;

    @Autowired private OrderMenuRepository  orderMenuRep;

    @Autowired
    private PizzaMenuRepository pizzaMenuRepository;


    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();
        pizzaRestaurantRepository.deleteAll();
        pizzaMenuRepository.deleteAll();
        orderMenuRep.deleteAll();



        RegisterUserRequest request = new RegisterUserRequest();

        request.setUsername("chargiePizza");
        request.setPassword("word");

        pizzaRestaurantService.registerRestaurant(request);

        CustomerRegisterUserRequest userRequest = new CustomerRegisterUserRequest();
        userRequest.setCustomerEmail("Kevin@yahoo.com");
        userRequest.setCustomerName("Ejiro Kompany");
        userRequest.setCustomerAddress("6 CORONA SCHOOL ,GRA Abijo");
        userRequest.setCustomerUserName("EjiroKompany");
        customerService.registerCustomer(userRequest);
    }

    @Test
    public void testToRegisterACustomer(){


        assertThat(customerRepository.count(),is(1L));
    }
    @Test public void testForCustomerRegistrationForTwoUsers(){

        CustomerRegisterUserRequest userRequest1 = new CustomerRegisterUserRequest();
        userRequest1.setCustomerEmail("Kevin@yahoo.com");
        userRequest1.setCustomerName("Joshua Kompany");
        userRequest1.setCustomerAddress("6 CORONA SCHOOL ,GRA Abijo");
        userRequest1.setCustomerUserName("Kompany");
        customerService.registerCustomer(userRequest1);

        assertThat(customerRepository.count(),is(2L));

    }
    @Test public void testForUniqueName(){

        CustomerRegisterUserRequest userRequest1 = new CustomerRegisterUserRequest();
        userRequest1.setCustomerEmail("Kevin@yahoo.com");
        userRequest1.setCustomerName("Ejiro Kompany");
        userRequest1.setCustomerAddress("6 CORONA SCHOOL ,GRA Abijo");
        userRequest1.setCustomerUserName("EjiroKompany");
        assertThrows(UserAlreadyExistException.class, ()-> customerService.registerCustomer(userRequest1));

    }

    @Test
    public void testLoginForCustomer(){
        CustomerRegisterUserRequest userRequest = new CustomerRegisterUserRequest();
        userRequest.setCustomerEmail("Kevin@yahoo.com");
        userRequest.setCustomerName("Ejiro Kompany");
        userRequest.setCustomerAddress("6 CORONA SCHOOL ,GRA Abijo");
        userRequest.setCustomerUserName("Don");
        userRequest.setCustomerPassword("NSG");
        customerService.registerCustomer(userRequest);


        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("Don");
        logInRequest.setPassword("NSG");

        customerService.unlock(logInRequest);

        Customer customer = new Customer();

        assertFalse(customer.isLock());

    }
    @Test public void testForUnregisterToLoginCustomer(){
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("Don");
        logInRequest.setPassword("NSG");

        assertThrows(UserAlreadyExistException.class,()->customerService.unlock(logInRequest));
    }
    @Test public void testForWrongPasswordAndUserNameLogin() {

        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("Don");
        logInRequest.setPassword("NSG");

        assertThrows(UserDoesNotExistException.class,()->
                customerService.unlock(logInRequest));

        CustomerRegisterUserRequest userRequest1 = new CustomerRegisterUserRequest();
        userRequest1.setCustomerEmail("Kevin@yahoo.com");
        userRequest1.setCustomerName("Joshua Kompany");
        userRequest1.setCustomerAddress("6 CORONA SCHOOL ,GRA Abijo");
        userRequest1.setCustomerUserName("Kompany");
        userRequest1.setCustomerPassword("NSG");
        customerService.registerCustomer(userRequest1);

        LogInRequest logInRequest2 = new LogInRequest();
        logInRequest2.setUsername("Kompany");
        logInRequest2.setPassword("word");

        assertThrows(InvalidPasswordException.class,()->
                customerService.unlock(logInRequest2));

    }


    @Test
    public void testCustomerCanGetFullMenuList() {

        AddMenuListRequest menuList = new AddMenuListRequest();
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));
        pizzaRestaurantService.addPizzaMenu(menuList);


        String expected = "Pizza Menu" + '\n' +
                "Pizza name : " + menuList.getPizzaName() + '\n' +
                "Pizza Size : " + menuList.getPizzaSize() + '\n' +
                "Pizza Price : " + menuList.getPizzaAmount() + '\n' +
                "Drink Name : " + menuList.getDrinkName() + '\n' +
                "Drink Price : " + menuList.getDrinkPrice() + '\n' +
                '\n';

        assertEquals(expected, customerService.menu("chargiePizza"));
    }
    @Test public void testForExceptions(){
        assertThrows(MenuNotFoundException.class,()->customerService.menu("chargiePizza"));
    }

    @Test
    public void testToOrder(){
        AddMenuListRequest menuList = new AddMenuListRequest();
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));
        menuList.setDrinkName("Coke");
        menuList.setDrinkPrice(BigDecimal.valueOf(100));
        pizzaRestaurantService.addPizzaMenu(menuList);

        OrderProductRequest orderProduct = new OrderProductRequest();
        orderProduct.setOrderName("Order1");
        orderProduct.setPizzaRestaurantName("chargiePizza");
        orderProduct.setCustomerName("EjiroKompany");
        orderProduct.setPizzaName("Margherita Pizza");
        orderProduct.setPizzaSize("small");
        orderProduct.setNumberOfPizza(3);
        orderProduct.setDrinks("Coke");
        orderProduct.setNumberOfDrinks(1);

        customerService.addOrderProduct(orderProduct);

        assertThat(orderMenuRep.count(),is(1L));

    }

    @Test
    public void  testToOrderForItemNotOnTheMenu(){

        AddMenuListRequest menuList = new AddMenuListRequest();
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));
        menuList.setDrinkName("Coke");
        menuList.setDrinkPrice(BigDecimal.valueOf(100));
        pizzaRestaurantService.addPizzaMenu(menuList);

        OrderProductRequest orderProduct = new OrderProductRequest();
        orderProduct.setOrderName("Order1");
        orderProduct.setPizzaRestaurantName("chargiePizza");
        orderProduct.setCustomerName("EjiroKompany");
        orderProduct.setPizzaName("Pizza");
        orderProduct.setPizzaSize("small");
        orderProduct.setNumberOfPizza(3);
        orderProduct.setDrinks("Fanta");
        orderProduct.setNumberOfDrinks(1);

        assertThrows(MenuNotFoundException.class,()->customerService.addOrderProduct(orderProduct));
    }

    @Test
    public void testToRemoveOrder(){
        AddMenuListRequest menuList = new AddMenuListRequest();
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));
        menuList.setDrinkName("Coke");
        menuList.setDrinkPrice(BigDecimal.valueOf(100));
        pizzaRestaurantService.addPizzaMenu(menuList);

        OrderProductRequest orderProduct = new OrderProductRequest();
        orderProduct.setOrderName("Order1");
        orderProduct.setPizzaRestaurantName("chargiePizza");
        orderProduct.setCustomerName("EjiroKompany");
        orderProduct.setPizzaName("Margherita Pizza");
        orderProduct.setPizzaSize("small");
        orderProduct.setNumberOfPizza(3);
        orderProduct.setDrinks("Coke");
        orderProduct.setNumberOfDrinks(1);

        customerService.addOrderProduct(orderProduct);

        assertThat(orderMenuRep.count(),is(1L));


        RemoveOrderRequest orderRequest = new RemoveOrderRequest();
        orderRequest.setOrderName("Order1");
        orderRequest.setPizzaRestaurantName("chargiePizza");
        orderRequest.setCustomerName("EjiroKompany");
        orderRequest.setPizzaName("Margherita Pizza");
        orderRequest.setPizzaSize("small");
        orderRequest.setNumberOfPizza(3);
        orderRequest.setDrinks("Coke");
        orderRequest.setNumberOfDrinks(1);

        pizzaRestaurantService.removeOrderRequest(orderRequest);
        customerService.removeOrder(orderRequest);


        assertThat(orderMenuRep.count(),is(0L));



    }




    }


