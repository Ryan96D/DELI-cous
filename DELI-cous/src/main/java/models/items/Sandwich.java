package models.items;

import enums.Bread;
import enums.Cheese;
import enums.Meat;
import enums.Size;
import enums.Topping;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {

    private Bread bread;
    private Size size;
    private boolean toasted;

    private List<Meat> meats;
    private Meat extraMeat;

    private List<Cheese> cheeses;
    private Cheese extraCheese;

    private List<Topping> toppings;

    // Constructor with bread and size
    public Sandwich(Bread bread, Size size) {
        this.bread = bread;
        this.size = size;
        this.toasted = false;

        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.toppings = new ArrayList<>();
    }

    // Getters and setters

    public Bread getBread() {
        return bread;
    }

    public void setBread(Bread bread) {
        this.bread = bread;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public List<Meat> getMeats() {
        return meats;
    }

    public void setMeats(List<Meat> meats) {
        this.meats = meats;
    }

    public Meat getExtraMeat() {
        return extraMeat;
    }

    public void setExtraMeat(Meat extraMeat) {
        this.extraMeat = extraMeat;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(List<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public Cheese getExtraCheese() {
        return extraCheese;
    }

    public void setExtraCheese(Cheese extraCheese) {
        this.extraCheese = extraCheese;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    // Add a single topping, max 10 toppings allowed
    public void addTopping(Topping topping) {
        if (this.toppings.size() >= 10) {
            System.out.println("The sandwich is too full.");
        } else {
            this.toppings.add(topping);
        }
    }

    // Optionally you can have a setter that replaces all toppings:
    public void setToppings(List<Topping> toppings) {
        if (toppings.size() > 10) {
            System.out.println("The sandwich is too full, adding only first 10 toppings.");
            this.toppings = toppings.subList(0, 10);
        } else {
            this.toppings = toppings;
        }
    }
}