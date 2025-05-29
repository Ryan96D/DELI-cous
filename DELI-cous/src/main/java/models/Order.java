package models;

import models.items.Sandwich;
import models.items.DrinkItem;
import models.items.Chips;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> sandwiches;
    private List<DrinkItem> drinks;
    private List<Chips> chips;

    public Order() {
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        chips = new ArrayList<>();
    }

    // Add items
    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(DrinkItem drink) {
        drinks.add(drink);
    }

    public void addChips(Chips chip) {
        chips.add(chip);  // add the chip parameter here
    }

    // Get lists
    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<DrinkItem> getDrinks() {
        return drinks;
    }

    public List<Chips> getChips() {
        return chips;
    }
}