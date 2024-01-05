package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.OrderMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.data.repositories.PizzaRestaurantRepository;
import com.chargiePizza.pizzaOrder.dtos.*;
import com.chargiePizza.pizzaOrder.expections.InvalidPasswordException;
import com.chargiePizza.pizzaOrder.expections.PizzaRestaurantAlreadyExistsException;

import com.chargiePizza.pizzaOrder.expections.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.chargiePizza.pizzaOrder.utils.mapper.map;

@Service
public class PizzaRestaurantServiceImpl implements PizzaRestaurantService {

    @Autowired
    private PizzaRestaurantRepository pizzaRestaurantRepository;

    @Autowired
    private PizzaMenuService pizzaMenuService;
    @Autowired
    private OrderMenuService  orderMenuService;

    @Override
    public void registerRestaurant(RegisterUserRequest registerUserRequest) {
        validateRestaurantUniqueName(registerUserRequest.getUsername());
        pizzaRestaurantRepository.save(map(registerUserRequest));

    }

    @Override
    public void unlock(LogInRequest loginRequest) {


        Optional<PizzaRestaurant> restaurantOptional = pizzaRestaurantRepository.findPizzaRestaurantByRestaurantName(loginRequest.getUsername());
        if (restaurantOptional.isEmpty()) {
            throw new InvalidPasswordException("Restaurant name and password are incorrect.");
        }

        PizzaRestaurant restaurant = restaurantOptional.get();
        if (restaurant.getRestaurantPassword().equals(loginRequest.getPassword())) {
            restaurant.setLock(false);
            pizzaRestaurantRepository.save(restaurant);
        } else {
            throw new InvalidPasswordException("IncorrectRestaurant name and password are ");
        }

    }


    @Override
    public  void addPizzaMenu(AddMenuListRequest menuList) {
        PizzaMenu pizzaMenu =  map(menuList);
        pizzaMenu.setPizzaRestaurant(getPizzaRestaurant(menuList.getPizzaRestaurantName()));
        pizzaMenuService.addMenu(pizzaMenu);
    }



    @Override
    public void removePizzaNameFromMenu(RemovePizzaMenuRequest removePizzaMenuRequest) {
        PizzaRestaurant pizzaRestaurant = getPizzaRestaurant(removePizzaMenuRequest.getRestaurantName());
        PizzaMenu pizzaMenu = pizzaMenuService.findPizzaMenu(removePizzaMenuRequest.getPizzaMenuName(), pizzaRestaurant);


        pizzaMenuService.remove(pizzaMenu);

    }


    public String getFullMenu(String restaurantName) {

         getPizzaRestaurant(restaurantName);
        return pizzaMenuService.getFullMenu();

    }


    @Override
    public void updatePizzaMenu(UpdateMenuRequest updateMenuRequest) {
        PizzaRestaurant pizzaRestaurant = getPizzaRestaurant(updateMenuRequest.getPizzaRestaurantName());
        PizzaMenu pizzaMenu = pizzaMenuService.findPizzaMenu(updateMenuRequest.getPizzaName(), pizzaRestaurant);


        pizzaMenuService.updatePizzaMenu(updateMenuRequest,pizzaMenu);
    }



@Override
public void receiveOrder(OrderMenu orderMenu) {
    PizzaRestaurant pizzaRestaurant = getPizzaRestaurant(orderMenu.getPizzaRestaurant().getRestaurantName());
    OrderMenu order = orderMenuService.findOrder(orderMenu.getOrderName(), pizzaRestaurant);
    pizzaMenuService.findPizzaMenu(order.getPizzaName(), pizzaRestaurant);


    List<OrderMenu> orderMenuList = pizzaRestaurant.getCustomerOrderMenu();
    if (orderMenuList == null) {
        orderMenuList = new ArrayList<>();
    }


    orderMenuList.add(order);
    pizzaRestaurant.setCustomerOrderMenu(orderMenuList);
    pizzaRestaurantRepository.save(pizzaRestaurant);
}


    @Override
    public List<OrderMenu> checkOrderMenuList(CheckMenuRequest request) {
        PizzaRestaurant pizzaRestaurant = getPizzaRestaurant(request.getPiazzaRestaurant());
        return pizzaRestaurant.getCustomerOrderMenu();
    }

    @Override
    public void removeOrderRequest(RemoveOrderRequest orderRequest) {
        PizzaRestaurant pizzaRestaurant = getPizzaRestaurant(orderRequest.getPizzaRestaurantName());
        List<OrderMenu>  customerOrderMenu = pizzaRestaurant.getCustomerOrderMenu();
        System.out.println(customerOrderMenu.toString());
        System.out.println(orderRequest.getOrderName());
        OrderMenu orderMenu = null;
        for (OrderMenu customerOrder : customerOrderMenu){
            if(orderRequest.getOrderName().equals(customerOrder.getOrderName())){
                orderMenu = customerOrder;
                System.out.println("found customer order");
            }
        }


        customerOrderMenu.remove(orderMenu);
        pizzaRestaurant.setCustomerOrderMenu(customerOrderMenu);
        pizzaRestaurantRepository.save(pizzaRestaurant);

    }


    @Override
    public boolean dispatchOrder(OrderMenu order) {
        return false;
    }

    private void validateRestaurantUniqueName(String restaurantName) {
        if (pizzaRestaurantRepository.findPizzaRestaurantByRestaurantNameIgnoreCase(restaurantName).isPresent()) {
            throw new PizzaRestaurantAlreadyExistsException("Pizza Restaurant Already Exists");

        }
    }

    @Override
    public PizzaRestaurant getPizzaRestaurant(String pizzaRestaurantName) {
        Optional<PizzaRestaurant> pizzaRestaurant =
                pizzaRestaurantRepository.findPizzaRestaurantByRestaurantNameIgnoreCase(pizzaRestaurantName);
        if(pizzaRestaurant.isEmpty()) throw new RestaurantNotFoundException("Pizza Restaurant Not Found");
        return pizzaRestaurant.get();
    }



}