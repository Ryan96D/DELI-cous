package models;

import models.items.Sandwich;
import models.items.Drinks;
import models.items.Chips;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> sandwiches;
    private List<Drinks> drinks;
    private List<Chips> chips;

    public Order() {
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        chips = new ArrayList<>();
    }

    public BigDecimal getTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;

        for (Sandwich sandwich : sandwiches) {
            total = total.add(sandwich.getPrice());
        }

        for (Drinks drink : drinks) {
            total = total.add(drink.getPrice());
        }

        for (Chips chip : chips) {
            total = total.add(chip.getPrice());
        }

        return total;
    }

    // Add items
    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drinks drinks) {
        this.drinks.add(drinks);
    }

    public void addChips(Chips chip) {
        chips.add(chip);  // add the chip parameter here
    }

    // Get lists
    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drinks> getDrinks() {
        return drinks;
    }

    public List<Chips> getChips() {
        return chips;
    }
}