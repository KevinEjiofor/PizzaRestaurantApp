package com.chargiePizza.pizzaOrder.services;



import java.math.BigDecimal;
import java.util.List;

import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.dtos.*;
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


    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    public void testToRegisterACustomer(){
        CustomerRegisterUserRequest userRequest = new CustomerRegisterUserRequest();
        userRequest.setCustomerEmail("Kevin@yahoo.com");
        userRequest.setCustomerName("Ejiro Kompany");
        userRequest.setCustomerAddress("6 CORONA SCHOOL ,GRA Abijo");
        userRequest.setCustomerUserName("EjiroKompany");
        customerService.registerCustomer(userRequest);

        assertThat(customerRepository.count(),is(1L));
    }
    @Test public void testForCustomerRegistrationForTwoUsers(){
        CustomerRegisterUserRequest userRequest = new CustomerRegisterUserRequest();
        userRequest.setCustomerEmail("Kevin@yahoo.com");
        userRequest.setCustomerName("Ejiro Kompany");
        userRequest.setCustomerAddress("6 CORONA SCHOOL ,GRA Abijo");
        userRequest.setCustomerUserName("EjiroKompany");
        customerService.registerCustomer(userRequest);

        CustomerRegisterUserRequest userRequest1 = new CustomerRegisterUserRequest();
        userRequest1.setCustomerEmail("Kevin@yahoo.com");
        userRequest1.setCustomerName("Joshua Kompany");
        userRequest1.setCustomerAddress("6 CORONA SCHOOL ,GRA Abijo");
        userRequest1.setCustomerUserName("Kompany");
        customerService.registerCustomer(userRequest1);

        assertThat(customerRepository.count(),is(2L));

    }
    @Test public void testForUniqueName(){
        CustomerRegisterUserRequest userRequest = new CustomerRegisterUserRequest();
        userRequest.setCustomerEmail("Kevin@yahoo.com");
        userRequest.setCustomerName("Ejiro Kompany");
        userRequest.setCustomerAddress("6 CORONA SCHOOL ,GRA Abijo");
        userRequest.setCustomerUserName("EjiroKompany");
        customerService.registerCustomer(userRequest);

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
        CustomerRegisterUserRequest userRequest = new CustomerRegisterUserRequest();
        userRequest.setCustomerEmail("Kevin@yahoo.com");
        userRequest.setCustomerName("Ejiro Kompany");
        userRequest.setCustomerAddress("6 CORONA SCHOOL ,GRA Abijo");
        userRequest.setCustomerUserName("EjiroKompany");
        userRequest.setCustomerPassword("NSG");
        customerService.registerCustomer(userRequest);

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

        RegisterUserRequest request = new RegisterUserRequest();

        request.setUsername("chargiePizza");
        request.setPassword("word");

        pizzaRestaurantService.registerRestaurant(request);

        AddMenuListRequest menuList = new AddMenuListRequest();
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));

        GetFullMenuRequest menuListRequest = new GetFullMenuRequest();
        menuListRequest.setPizzaRestaurantName("chargiePizza");
        menuListRequest.setPizzaMenuName("Margherita");


        List<PizzaMenu> menu = customerService.menu(menuListRequest);


        assertThat(menu.size(), is(1));
    }







    }


