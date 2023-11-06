package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.data.repositories.PizzaMenuRepository;
import com.chargiePizza.pizzaOrder.data.repositories.PizzaRestaurantRepository;
import com.chargiePizza.pizzaOrder.dtos.*;

import com.chargiePizza.pizzaOrder.expections.InvalidPasswordException;
import com.chargiePizza.pizzaOrder.expections.MenuNotFoundException;
import com.chargiePizza.pizzaOrder.expections.PizzaRestaurantAlreadyExistsException;
import com.chargiePizza.pizzaOrder.expections.RestaurantNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PizzaRestaurantServiceImplTest {
    @Autowired
    private PizzaRestaurantService pizzaRestaurantService ;
    @Autowired
    private PizzaRestaurantRepository pizzaRestaurantRepository;

    @Autowired
    private PizzaMenuRepository pizzaMenuRepository;

    @BeforeEach
    public void deleteBeforeEachTest(){
        pizzaRestaurantRepository.deleteAll();
        pizzaMenuRepository.deleteAll();
    }

    @Test
    public void testPizzaShopCanRegister(){
        RegisterUserRequest request = new RegisterUserRequest();

        request.setUsername("chargiePizza");
        request.setPassword("word");

        pizzaRestaurantService.registerRestaurant(request);
        assertThat(pizzaRestaurantRepository.count(), is(1L));

    }

    @Test
    public void testForPizzaRestaurantDoesNotHaveTheSameRestaurantName(){
        RegisterUserRequest request = new RegisterUserRequest();

        request.setUsername("chargiePizza");
        request.setPassword("word");

        pizzaRestaurantService.registerRestaurant(request);
        assertThat(pizzaRestaurantRepository.count(), is(1L));

        RegisterUserRequest request2 = new RegisterUserRequest();
        request2.setUsername("chargiePizza");
        request.setPassword("word");

        assertThrows(PizzaRestaurantAlreadyExistsException.class,()->pizzaRestaurantService.registerRestaurant(request2));

    }

    @Test
    public void testForPizzaRestaurantDoesNotHaveTheSameRestaurantNameAndEvenWhenIsADifferentCasing(){
        RegisterUserRequest request = new RegisterUserRequest();

        request.setUsername("chargiePizza");
        request.setPassword("word");

        pizzaRestaurantService.registerRestaurant(request);
        assertThat(pizzaRestaurantRepository.count(), is(1L));

        RegisterUserRequest request2 = new RegisterUserRequest();
        request2.setUsername("ChargiePizza");
        request.setPassword("word");

        assertThrows(PizzaRestaurantAlreadyExistsException.class,()->pizzaRestaurantService.registerRestaurant(request2));
        assertThat(pizzaRestaurantRepository.count(), is(1L));

    }

    @Test public void testLogInForRestaurant(){
        PizzaRestaurant pizza = new PizzaRestaurant();
        RegisterUserRequest request = new RegisterUserRequest();

        request.setUsername("chargiePizza");
        request.setPassword("word");

        pizzaRestaurantService.registerRestaurant(request);


        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("chargiePizza");
        logInRequest.setPassword("word");

        pizzaRestaurantService.unlock(logInRequest);

        assertFalse(pizza.isLock());

    }


    @Test
    public void testToLogInUnregisterRestaurant(){

        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("chargiePizza");
        logInRequest.setPassword("word");

       assertThrows(InvalidPasswordException.class,()-> pizzaRestaurantService.unlock(logInRequest));
    }

    @Test
    public void testToLoginWithDetails(){

        RegisterUserRequest request = new RegisterUserRequest();

        request.setUsername("chargiePizza");
        request.setPassword("word");

        pizzaRestaurantService.registerRestaurant(request);


        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("chargiePizza");
        logInRequest.setPassword("wordeee");


        assertThrows(InvalidPasswordException.class,()->pizzaRestaurantService.unlock(logInRequest));

    }

    @Test
    public void testToAddToMenu() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("chargiePizza");
        request.setPassword("word");
        pizzaRestaurantService.registerRestaurant(request);

        AddMenuListRequest  menuList = new AddMenuListRequest ();
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));

        pizzaRestaurantService.addPizzaMenu(menuList);

        assertThat(pizzaMenuRepository.count(), is(1L));
    }

    @Test
    public void testToAddDifferentPizzaToMenu() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("chargiePizza");
        request.setPassword("word");
        pizzaRestaurantService.registerRestaurant(request);

        AddMenuListRequest  menuList = new AddMenuListRequest ();
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));

        pizzaRestaurantService.addPizzaMenu(menuList);




        AddMenuListRequest  menuItem1 = new AddMenuListRequest ();

        menuItem1.setPizzaRestaurantName("chargiePizza");
        menuItem1.setPizzaName("BBQ Chicken Pizza.");
        menuItem1.setPizzaSize("Large");
        menuItem1.setPizzaAmount(BigDecimal.valueOf(10));

        pizzaRestaurantService.addPizzaMenu(menuItem1);

        assertThat(pizzaMenuRepository.count(), is(2L));
    }
    @Test
    public void testToAddPizzaMenuToUnRegisterRestaurant(){

        AddMenuListRequest  menuList = new AddMenuListRequest ();
        menuList.setPizzaRestaurantName("chargie");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));

       assertThrows(RestaurantNotFoundException.class,()-> pizzaRestaurantService.addPizzaMenu(menuList));

    }

    @Test
    public void testToRemoveFromTheMenu(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("chargiePizza");
        request.setPassword("word");
        pizzaRestaurantService.registerRestaurant(request);

        AddMenuListRequest  menuList = new AddMenuListRequest ();
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));

        pizzaRestaurantService.addPizzaMenu(menuList);

        AddMenuListRequest  menuItem1 = new AddMenuListRequest ();
        menuItem1.setPizzaRestaurantName("chargiePizza");
        menuItem1.setPizzaName("BBQ Chicken Pizza.");
        menuItem1.setPizzaSize("Large");
        menuItem1.setPizzaAmount(BigDecimal.valueOf(10));
        pizzaRestaurantService.addPizzaMenu(menuItem1);


        PizzaRestaurantNameRequest restaurantNameRequest = new PizzaRestaurantNameRequest();
        restaurantNameRequest.setPizzaRestaurantName("chargiePizza");


    }
    @Test
    public void testRemoveFromTheMenu() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("chargiePizza");
        request.setPassword("word");
        pizzaRestaurantService.registerRestaurant(request);

        AddMenuListRequest  menuList = new AddMenuListRequest ();
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));
        pizzaRestaurantService.addPizzaMenu(menuList);




        RemovePizzaMenuRequest removePizzaMenuRequest = new RemovePizzaMenuRequest();
        removePizzaMenuRequest.setPizzaMenuName("Margherita Pizza");
        removePizzaMenuRequest.setRestaurantName("chargiePizza");

        pizzaRestaurantService.removePizzaNameFromMenu(removePizzaMenuRequest);
        assertThat(pizzaMenuRepository.count(), is(0L));
    }


    @Test
    public void testRemoveForCaseSensitivity(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("chargiePizza");
        request.setPassword("word");
        pizzaRestaurantService.registerRestaurant(request);

        AddMenuListRequest  menuList = new AddMenuListRequest ();
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));
        pizzaRestaurantService.addPizzaMenu(menuList);

        assertThat(pizzaMenuRepository.count(), is(1L));


        RemovePizzaMenuRequest removePizzaMenuRequest = new RemovePizzaMenuRequest();
        removePizzaMenuRequest.setPizzaMenuName("margherita Pizza");
        removePizzaMenuRequest.setRestaurantName("chargiePizza");

        pizzaRestaurantService.removePizzaNameFromMenu(removePizzaMenuRequest);

        assertThat(pizzaMenuRepository.count(), is(0L));
    }

    @Test
    public void testForExceptionPizzaNameAndRestaurant(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("chargiePizza");
        request.setPassword("word");

        pizzaRestaurantService.registerRestaurant(request);

        AddMenuListRequest  menuList = new AddMenuListRequest ();
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));
        pizzaRestaurantService.addPizzaMenu(menuList);


        RemovePizzaMenuRequest removePizzaMenuRequest = new RemovePizzaMenuRequest();
        removePizzaMenuRequest.setPizzaMenuName("Margherita ");
        removePizzaMenuRequest.setRestaurantName("chargiePizzzza");

        assertThrows(PizzaRestaurantAlreadyExistsException.class,()->
                        pizzaRestaurantService.removePizzaNameFromMenu(removePizzaMenuRequest));


    }














}