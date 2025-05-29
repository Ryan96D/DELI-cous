package models.items;

import enums.Drink;
import enums.Size;

public class DrinkItem {
    private Drink drink;
    private Size size;

    public DrinkItem(Drink drink, Size size) {
        this.drink = drink;
        this.size = size;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size + " " + drink;
    }
}