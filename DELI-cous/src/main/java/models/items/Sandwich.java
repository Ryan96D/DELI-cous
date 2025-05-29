package models.items;

import enums.Bread;
import enums.Cheese;
import enums.Meat;
import enums.Size;
import enums.Sauce;
import enums.Topping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Sandwich implements PricedItem {
    private Bread bread;
    private Size size;
    private List<Meat> meats;
    private List<Cheese> cheeses;
    private List<Topping> toppings;
    private List<Sauce> sauces;
    private boolean toasted;

    public Sandwich(Bread bread, Size size) {
        this.bread = bread;
        this.size = size;
        this.sauces = new ArrayList<>();
    }

    // Getters
    public Bread getBread() {
        return bread;
    }

    public Size getSize() {
        return size;
    }

    public List<Meat> getMeats() {
        return meats;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public List<Sauce> getSauces() {
        return sauces;
    }

    public boolean isToasted() {
        return toasted;
    }

    // Setters
    public void setMeats(List<Meat> meats) {
        this.meats = meats;
    }

    public void setCheeses(List<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public void setSauces(List<Sauce> sauces) {
        this.sauces = sauces;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal total = switch (size) {
            case SMALL -> new BigDecimal("5.50");
            case MEDIUM -> new BigDecimal("7.00");
            case LARGE -> new BigDecimal("8.50");
        };

        // Meat prices
        if (meats != null && !meats.isEmpty()) {
            BigDecimal baseMeatPrice = switch (size) {
                case SMALL -> new BigDecimal("1.00");
                case MEDIUM -> new BigDecimal("2.00");
                case LARGE -> new BigDecimal("3.00");
            };
            BigDecimal extraMeatPrice = switch (size) {
                case SMALL -> new BigDecimal("0.50");
                case MEDIUM -> new BigDecimal("1.00");
                case LARGE -> new BigDecimal("1.50");
            };

            total = total.add(baseMeatPrice);
            if (meats.size() > 1) {
                total = total.add(extraMeatPrice.multiply(BigDecimal.valueOf(meats.size() - 1)));
            }
        }

        // Cheese prices
        if (cheeses != null && !cheeses.isEmpty()) {
            BigDecimal baseCheesePrice = switch (size) {
                case SMALL -> new BigDecimal("0.75");
                case MEDIUM -> new BigDecimal("1.50");
                case LARGE -> new BigDecimal("2.25");
            };
            BigDecimal extraCheesePrice = switch (size) {
                case SMALL -> new BigDecimal("0.30");
                case MEDIUM -> new BigDecimal("0.60");
                case LARGE -> new BigDecimal("0.90");
            };

            total = total.add(baseCheesePrice);
            if (cheeses.size() > 1) {
                total = total.add(extraCheesePrice.multiply(BigDecimal.valueOf(cheeses.size() - 1)));
            }
        }

        return total;
    }

    @Override
    public String getReceiptLine() {
        return size + " Sandwich on " + bread + " - $" + getPrice();
    }
}