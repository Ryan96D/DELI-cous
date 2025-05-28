package models.items;

import enums.*;
import java.math.BigDecimal;
import java.util.List;

public class Sandwich implements PricedItem {

    private BreadType breadType;
    private Size size;
    private List<Meat> meats;
    private List<Cheese> cheeses;
    private List<RegularTopping> regularToppings;
    private List<Sauce> sauces;
    private boolean toasted;

    private int extraMeatCount;
    private int extraCheeseCount;

    public Sandwich(BreadType breadType, Size size,
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
        StringBuilder line = new StringBuilder();

        line.append(size).append(" ").append(breadType).append(" Sandwich");
        if (toasted) {
            line.append(" (Toasted)");
        }
        line.append("\n");

        if (!meats.isEmpty()) {
            line.append("  Meats: ").append(meats).append("\n");
        }
        if (!cheeses.isEmpty()) {
            line.append("  Cheeses: ").append(cheeses).append("\n");
        }
        if (!regularToppings.isEmpty()) {
            line.append("  Toppings: ").append(regularToppings).append("\n");
        }
        if (!sauces.isEmpty()) {
            line.append("  Sauces: ").append(sauces).append("\n");
        }
        if (extraMeatCount > 0) {
            line.append("  Extra Meat x").append(extraMeatCount).append("\n");
        }
        if (extraCheeseCount > 0) {
            line.append("  Extra Cheese x").append(extraCheeseCount).append("\n");
        }

        line.append("  Price: $").append(getPrice());

        return line.toString();
    }
}