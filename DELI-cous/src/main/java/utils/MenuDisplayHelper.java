package utils;

import enums.*;

import java.math.BigDecimal;

public class MenuDisplayHelper {

    // Show menu items with prices 
    public static <T extends Enum<T>> void displayOptionsWithPrices(String category, T[] items, Size size, boolean isExtra) {
        System.out.println("\n" + (isExtra ? "Extra " : "") + capitalize(category) + " Options:");
        for (int i = 0; i < items.length; i++) {
            BigDecimal price = getPrice(items[i], size, isExtra);
            System.out.printf("%d) %s - %s%s%n", i + 1, items[i], formatPrice(price), (isExtra ? " (Extra)" : ""));
        }
    }

    // Get price of an item based on size and extra flag
    private static BigDecimal getPrice(Enum<?> item, Size size, boolean isExtra) {
        int index = size.ordinal();

        if (item instanceof Meat) {
            return isExtra ? new BigDecimal[] { new BigDecimal("0.50"), new BigDecimal("1.00"), new BigDecimal("1.50") }[index]
                    : new BigDecimal[] { new BigDecimal("1.00"), new BigDecimal("2.00"), new BigDecimal("3.00") }[index];
        } else if (item instanceof Cheese) {
            return isExtra ? new BigDecimal[] { new BigDecimal("0.30"), new BigDecimal("0.60"), new BigDecimal("0.90") }[index]
                    : new BigDecimal[] { new BigDecimal("0.75"), new BigDecimal("1.50"), new BigDecimal("2.25") }[index];
        } else if (item instanceof Bread) {
            return new BigDecimal[] { new BigDecimal("5.50"), new BigDecimal("7.00"), new BigDecimal("8.50") }[index];
        } else if (item instanceof Topping || item instanceof Sauce) {
            return BigDecimal.ZERO;
        } else if (item instanceof ChipFlavor) {
            return new BigDecimal("1.50");
        } else if (item instanceof DrinkFlavor) {
            return new BigDecimal[] { new BigDecimal("2.00"), new BigDecimal("2.50"), new BigDecimal("3.00") }[index];
        }
        return BigDecimal.ZERO;
    }

    // Format price for display
    private static String formatPrice(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) == 0 ? "Included" : "$" + price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    //Capitalize first letter
    private static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}