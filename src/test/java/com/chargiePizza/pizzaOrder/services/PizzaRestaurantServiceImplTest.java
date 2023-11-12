package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.data.repositories.CustomerRepository;
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
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private  PizzaMenuService pizzaMenuService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;


    @BeforeEach
    public void deleteBeforeEachTest(){
        pizzaRestaurantRepository.deleteAll();
        pizzaMenuRepository.deleteAll();
        customerRepository.deleteAll();

        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("chargiePizza");
        request.setPassword("word");
        pizzaRestaurantService.registerRestaurant(request);
    }

    @Test
    public void testPizzaShopCanRegister(){

        assertThat(pizzaRestaurantRepository.count(), is(1L));

    }

    @Test
    public void testForPizzaRestaurantDoesNotHaveTheSameRestaurantName(){

        assertThat(pizzaRestaurantRepository.count(), is(1L));

        RegisterUserRequest request2 = new RegisterUserRequest();
        request2.setUsername("chargiePizza");
        request2.setPassword("word");

        assertThrows(PizzaRestaurantAlreadyExistsException.class,()->pizzaRestaurantService.registerRestaurant(request2));

    }

    @Test
    public void testForPizzaRestaurantDoesNotHaveTheSameRestaurantNameAndEvenWhenTheLetterCasingIsDifferent(){

        assertThat(pizzaRestaurantRepository.count(), is(1L));

        RegisterUserRequest request2 = new RegisterUserRequest();
        request2.setUsername("ChargiePizza");
        request2.setPassword("word");

        assertThrows(PizzaRestaurantAlreadyExistsException.class,()->pizzaRestaurantService.registerRestaurant(request2));
        assertThat(pizzaRestaurantRepository.count(), is(1L));

    }

    @Test public void testLogInForRestaurant(){
        PizzaRestaurant pizzaRestaurant = new PizzaRestaurant();



        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("chargiePizza");
        logInRequest.setPassword("word");

        pizzaRestaurantService.unlock(logInRequest);

        assertFalse(pizzaRestaurant.isLock());

    }


    @Test
    public void testToLogInUnregisterRestaurant(){

        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("chargie");
        logInRequest.setPassword("word");

       assertThrows(InvalidPasswordException.class,()-> pizzaRestaurantService.unlock(logInRequest));
    }

    @Test
    public void testToLoginWithWrongDetails(){;


        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("chargiePizza");
        logInRequest.setPassword("wordeee");


        assertThrows(InvalidPasswordException.class,()->pizzaRestaurantService.unlock(logInRequest));

    }

    @Test
    public void testToAddToMenu() {

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
    public void testRemoveMethodForCaseSensitivity(){


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
    public void testForExceptionForWrongPizzaNameAndRestaurant(){

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



    @Test
    public void testToUpdateMenu() {

        AddMenuListRequest menuList = new AddMenuListRequest();
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setPizzaAmount(BigDecimal.valueOf(10));
        pizzaRestaurantService.addPizzaMenu(menuList);

        UpdateMenuRequest updateRequest = new UpdateMenuRequest();
        updateRequest.setPizzaRestaurantName("chargiePizza");
        updateRequest.setPizzaName("Margherita Pizza");
        updateRequest.setNewPizzaSize("large");
        updateRequest.setNewPizzaAmount(BigDecimal.valueOf(20));
        pizzaRestaurantService.updatePizzaMenu(updateRequest);

        PizzaMenu updatedPizzaMenu = pizzaMenuService.findPizzaMenu("Margherita Pizza", getPizzaRestaurantForTest(updateRequest.getPizzaRestaurantName()));


        assertNotNull(updatedPizzaMenu);
        assertEquals("large", updatedPizzaMenu.getPizzaSize());
        assertEquals(BigDecimal.valueOf(20), updatedPizzaMenu.getPizzaPrice());
    }
    
    @Test
    public void testUpdatePizzaExceptions(){


        UpdateMenuRequest updateRequest = new UpdateMenuRequest();
        updateRequest.setPizzaRestaurantName("chargiePizza");
        updateRequest.setPizzaName("Margherita Pizza");
        updateRequest.setNewPizzaSize("large");
        updateRequest.setNewPizzaAmount(BigDecimal.valueOf(20));


        assertThrows(MenuNotFoundException.class,()->pizzaRestaurantService.updatePizzaMenu(updateRequest));


    }



    @Test
    public void testToGetFullMenu() {
        AddMenuListRequest menuItem1 = new AddMenuListRequest();
        menuItem1.setPizzaRestaurantName("chargiePizza");
        menuItem1.setPizzaName("BBQ Chicken Pizza");
        menuItem1.setPizzaSize("Large");
        menuItem1.setPizzaAmount(BigDecimal.valueOf(10));
        pizzaRestaurantService.addPizzaMenu(menuItem1);

        AddMenuListRequest menuList = new AddMenuListRequest();
        menuList.setPizzaRestaurantName("chargiePizza");
        menuList.setPizzaName("Margherita Pizza");
        menuList.setPizzaSize("small");
        menuList.setDrinkName("Coke");
        menuList.setDrinkPrice(BigDecimal.valueOf(100));
        menuList.setPizzaAmount(BigDecimal.valueOf(10));
        pizzaRestaurantService.addPizzaMenu(menuList);
        assertThat(pizzaMenuRepository.count(), is(2L));


        String expected =  "Pizza Menu"+'\n' +
                "Pizza name : " + menuItem1.getPizzaName()+ '\n' +
                "Pizza Size : " + menuItem1.getPizzaSize() + '\n' +
                "Pizza Price : " + menuItem1.getPizzaAmount() + '\n' +
                "Drink Name : " + menuItem1.getDrinkName()+ '\n' +
                "Drink Price : " + menuItem1.getDrinkPrice() +'\n'+
                '\n'+
                "Pizza Menu"+'\n' +
                "Pizza name : " + menuList.getPizzaName()+ '\n' +
                "Pizza Size : " + menuList.getPizzaSize() + '\n' +
                "Pizza Price : " + menuList.getPizzaAmount() + '\n' +
                "Drink Name : " + menuList.getDrinkName()+ '\n' +
                "Drink Price : " + menuList.getDrinkPrice() +'\n'+
                '\n';






        assertEquals(expected, pizzaRestaurantService.getFullMenu("chargiePizza"));
    }
    @Test public void testForEmptyMenu() {
        assertThrows(MenuNotFoundException.class,()->pizzaRestaurantService.getFullMenu("chargiePizza"));

    }

    private PizzaRestaurant getPizzaRestaurantForTest(String restaurantName) {
        Optional<PizzaRestaurant> pizzaRestaurantOptional = pizzaRestaurantRepository.findPizzaRestaurantByRestaurantNameIgnoreCase(restaurantName);
        return pizzaRestaurantOptional.orElseThrow(() -> new RestaurantNotFoundException("Pizza Restaurant Not Found"));
    }


    @Test public void testThatPizzaRestaurantReceivedOrder(){
        AddMenuListRequest menuItem1 = new AddMenuListRequest();
        menuItem1.setPizzaRestaurantName("chargiePizza");
        menuItem1.setPizzaName("BBQ Chicken Pizza");
        menuItem1.setPizzaSize("Large");
        menuItem1.setPizzaAmount(BigDecimal.valueOf(10));
        pizzaRestaurantService.addPizzaMenu(menuItem1);

        CustomerRegisterUserRequest userRequest1 = new CustomerRegisterUserRequest();
        userRequest1.setCustomerEmail("Kevin@yahoo.com");
        userRequest1.setCustomerName("Kompany");
        userRequest1.setCustomerAddress("6 CORONA SCHOOL ,GRA Abijo");
        userRequest1.setCustomerUserName("Kompany");
        customerService.registerCustomer(userRequest1);

        OrderProductRequest orderProduct = new OrderProductRequest();
        orderProduct.setOrderName("order1");
        orderProduct.setCustomerName("Kompany");
        orderProduct.setPizzaName("Margherita Pizza");
        orderProduct.setPizzaSize("small");
        orderProduct.setNumberOfPizza(3);
        orderProduct.setDrinks("Coke");
        orderProduct.setNumberOfDrinks(1);
        orderProduct.setPizzaRestaurantName("chargiePizza");
        customerService.addOrderProduct(orderProduct);

        CheckMenuRequest checkRequest = new CheckMenuRequest();
        checkRequest.setPiazzaRestaurant("chargiePizza");
        List<OrderMenu> orderMenuList = pizzaRestaurantService.checkOrderMenuList(checkRequest);

        assertThat(orderMenuList.size(), is(1L));





    }
}