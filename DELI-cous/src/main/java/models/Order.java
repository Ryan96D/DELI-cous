package models;

import models.items.Sandwich;
import models.items.DrinkItem;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> sandwiches;
    private List<DrinkItem> drinks;


    public Order() {
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();

    }

    // Add items
    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(DrinkItem drink) {
        drinks.add(drink);
    }



    // Get lists
    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<DrinkItem> getDrinks() {
        return drinks;
    }




}