package models.items;

import enums.*;
import java.math.BigDecimal;
import java.util.List;

public class Sandwich implements PricedItem {

    private Bread breadType;
    private Size size;
    private List<Meat> meats;
    private List<Cheese> cheeses;
    private List<RegularTopping> regularToppings;
    private List<Sauce> sauces;
    private boolean toasted;

    private int extraMeatCount;
    private int extraCheeseCount;

    public Sandwich(Bread breadType, Size size) {
        this.breadType = breadType;
        this.size = size;
    }

    public Sandwich(Bread breadType, Size size,
                    List<Meat> meats, List<Cheese> cheeses,
                    List<RegularTopping> regularToppings, List<Sauce> sauces,
                    boolean toasted, int extraMeatCount, int extraCheeseCount) {
        this.breadType = breadType;
        this.size = size;
        this.meats = meats;
        this.cheeses = cheeses;
        this.regularToppings = regularToppings;
        this.sauces = sauces;
        this.toasted = toasted;
        this.extraMeatCount = extraMeatCount;
        this.extraCheeseCount = extraCheeseCount;
    }

    public Bread getBreadType() {
        return breadType;
    }

    public void setBreadType(Bread breadType) {
        this.breadType = breadType;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<Meat> getMeats() {
        return meats;
    }

    public void setMeats(List<Meat> meats) {
        this.meats = meats;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(List<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public List<RegularTopping> getRegularToppings() {
        return regularToppings;
    }

    public void setRegularToppings(List<RegularTopping> regularToppings) {
        this.regularToppings = regularToppings;
    }

    public List<Sauce> getSauces() {
        return sauces;
    }

    public void setSauces(List<Sauce> sauces) {
        this.sauces = sauces;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public int getExtraMeatCount() {
        return extraMeatCount;
    }

    public void setExtraMeatCount(int extraMeatCount) {
        this.extraMeatCount = extraMeatCount;
    }

    public int getExtraCheeseCount() {
        return extraCheeseCount;
    }

    public void setExtraCheeseCount(int extraCheeseCount) {
        this.extraCheeseCount = extraCheeseCount;
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal total = switch (size) {
            case SMALL -> new BigDecimal("5.50");
            case MEDIUM -> new BigDecimal("7.00");
            case LARGE -> new BigDecimal("8.50");
        };

        BigDecimal meatPrice = switch (size) {
            case SMALL -> new BigDecimal("1.00");
            case MEDIUM -> new BigDecimal("2.00");
            case LARGE -> new BigDecimal("3.00");
        };
        total = total.add(meatPrice.multiply(BigDecimal.valueOf(meats.size())));

        BigDecimal extraMeatPrice = switch (size) {
            case SMALL -> new BigDecimal("0.50");
            case MEDIUM -> new BigDecimal("1.00");
            case LARGE -> new BigDecimal("1.50");
        };
        total = total.add(extraMeatPrice.multiply(BigDecimal.valueOf(extraMeatCount)));

        BigDecimal cheesePrice = switch (size) {
            case SMALL -> new BigDecimal("0.75");
            case MEDIUM -> new BigDecimal("1.50");
            case LARGE -> new BigDecimal("2.25");
        };
        total = total.add(cheesePrice.multiply(BigDecimal.valueOf(cheeses.size())));

        BigDecimal extraCheesePrice = switch (size) {
            case SMALL -> new BigDecimal("0.30");
            case MEDIUM -> new BigDecimal("0.60");
            case LARGE -> new BigDecimal("0.90");
        };
        total = total.add(extraCheesePrice.multiply(BigDecimal.valueOf(extraCheeseCount)));

        return total;
    }

    @Override
    public String getReceiptLine() {
        return "";
    }
}