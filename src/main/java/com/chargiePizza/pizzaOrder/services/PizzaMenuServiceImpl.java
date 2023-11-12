package com.chargiePizza.pizzaOrder.services;

import com.chargiePizza.pizzaOrder.data.models.PizzaMenu;
import com.chargiePizza.pizzaOrder.data.models.PizzaRestaurant;
import com.chargiePizza.pizzaOrder.data.repositories.PizzaMenuRepository;

import com.chargiePizza.pizzaOrder.dtos.UpdateMenuRequest;
import com.chargiePizza.pizzaOrder.expections.MenuNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.chargiePizza.pizzaOrder.utils.mapper.map;

@Service
public class PizzaMenuServiceImpl implements PizzaMenuService{
    @Autowired
    private PizzaMenuRepository pizzaMenuRepository;

    @Override
    public void remove(PizzaMenu pizzaMenu) {
        pizzaMenuRepository.delete(pizzaMenu);

    }

    @Override
    public void addMenu(PizzaMenu pizzaMenu) {
        pizzaMenuRepository.save(pizzaMenu);
    }
    @Override
    public PizzaMenu findPizzaMenu(String pizzaMenuName, PizzaRestaurant pizzaRestaurant) {
        Optional<PizzaMenu> pizzaMenu = pizzaMenuRepository
                .findPizzaMenuByPizzaNameIgnoreCaseAndPizzaRestaurant(pizzaMenuName, pizzaRestaurant);
        if(pizzaMenu.isEmpty()) throw new MenuNotFoundException("Pizza Menu Not Found");
        return pizzaMenu.get();
    }




    @Override
    public String getFullMenu() {
        List<PizzaMenu> menuItems = pizzaMenuRepository.findAll();
        StringBuilder fullMenu = new StringBuilder();
        if(menuItems.isEmpty()) throw new MenuNotFoundException("Pizza Menu is Empty");
        for (PizzaMenu menuItem : menuItems) {
            fullMenu.append(menuItem.toString());
        }
        return  fullMenu.toString();
    }





    @Override
    public void updatePizzaMenu(UpdateMenuRequest updateMenuRequest,PizzaMenu pizzaMenu) {


        PizzaMenu updatedPizzaMenu = map(updateMenuRequest,pizzaMenu);
        pizzaMenuRepository.save(updatedPizzaMenu);
    }




}
