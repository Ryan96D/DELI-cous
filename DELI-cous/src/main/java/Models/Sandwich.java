package Models;

import Enums.BreadType;
import Enums.Sauce;
import Enums.Size;

import java.math.BigDecimal;
import java.util.List;

public class Sandwich implements PricedItem {
    private Size size;
    private BreadType breadType;
    private boolean toasted;
    private List<Toppings> meats;
    private List<Toppings> cheeses;
    private List<Toppings> regularToppings;
    private List<Sauce> sauces;

    public Sandwich(Size size, BreadType breadType, boolean toasted, List<Toppings> meats, List<Toppings> cheeses, List<Toppings> regularToppings, List<Sauce> sauces) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
        this.meats = meats;
        this.cheeses = cheeses;
        this.regularToppings = regularToppings;
        this.sauces = sauces;
    }

    @Override
    public BigDecimal getPrice() {
        return null; //get Price from PriceCalculator (later)
    }

    @Override
    public String getReceiptLine() {
        return size + " " + breadType + " Sandwich - $" + getPrice();
    }

}
