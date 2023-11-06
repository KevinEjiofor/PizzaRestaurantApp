package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.Order;
import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.data.repositories.PizzaRestaurantRepository;
import com.chargiePizza.pizzaOrder.dtos.*;
import com.chargiePizza.pizzaOrder.expections.InvalidPasswordException;
import com.chargiePizza.pizzaOrder.expections.PizzaRestaurantAlreadyExistsException;

import com.chargiePizza.pizzaOrder.expections.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.chargiePizza.pizzaOrder.utils.mapper.map;

@Service
public class PizzaRestaurantServiceImpl implements PizzaRestaurantService {

    @Autowired
    private PizzaRestaurantRepository pizzaRestaurantRepository;
    @Autowired
    private PizzaMenuService pizzaMenuService;

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
     PizzaMenu pizzaMenu = pizzaMenuService.findPizzaMenu(removePizzaMenuRequest, pizzaRestaurant);

     pizzaMenuService.remove(pizzaMenu);

    }

    @Override
    public List<PizzaMenu> getFullMenu(GetFullMenuRequest request) {
        return null;
    }


    @Override
    public PizzaMenu updatePizzaMenu(AddMenuListRequest menuList) {
        return null;
    }


    @Override
    public void receiveOrder() {

    }

    @Override
    public void updateOrder() {

    }

    @Override
    public boolean dispatchOrder(Order order) {
        return false;
    }

    private void validateRestaurantUniqueName(String restaurantName) {
        if (pizzaRestaurantRepository.findPizzaRestaurantByRestaurantNameIgnoreCase(restaurantName).isPresent()) {
            throw new PizzaRestaurantAlreadyExistsException("Pizza Restaurant Already Exists");

        }
    }
    private PizzaRestaurant getPizzaRestaurant(String pizzaRestaurantName) {
        Optional<PizzaRestaurant> pizzaRestaurant =
                pizzaRestaurantRepository.findPizzaRestaurantByRestaurantNameIgnoreCase(pizzaRestaurantName);
        if(pizzaRestaurant.isEmpty()) throw new RestaurantNotFoundException("Pizza Restaurant Not Found");
        return pizzaRestaurant.get();
    }
}