package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.data.repositories.PizzaMenuRepository;

import com.chargiePizza.pizzaOrder.dtos.GetFullMenuRequest;
import com.chargiePizza.pizzaOrder.dtos.RemovePizzaMenuRequest;
import com.chargiePizza.pizzaOrder.expections.MenuNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaMenuServiceImpl implements PizzaMenuService{
    @Autowired
    private PizzaMenuRepository pizzaMenuRepository;
    @Autowired
    private PizzaRestaurantService pizzaRestaurantService;
    @Override
    public void remove(PizzaMenu pizzaMenu) {
        pizzaMenuRepository.delete(pizzaMenu);

    }

    @Override
    public void addMenu(PizzaMenu pizzaMenu) {
        pizzaMenuRepository.save(pizzaMenu);
    }

    @Override
    public PizzaMenu findPizzaMenu(RemovePizzaMenuRequest removePizzaMenuRequest, PizzaRestaurant pizzaRestaurant) {
        Optional<PizzaMenu> pizzaMenu = pizzaMenuRepository
                .findPizzaMenuByPizzaNameIgnoreCaseAndPizzaRestaurant(removePizzaMenuRequest.getPizzaMenuName(),
                        pizzaRestaurant);
        if(pizzaMenu.isEmpty()) throw new MenuNotFoundException("Pizza Menu Not Found");
        return pizzaMenu.get();
    }





}
