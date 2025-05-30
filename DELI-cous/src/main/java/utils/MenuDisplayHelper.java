package utils;

import enums.*;

import java.math.BigDecimal;

public class MenuDisplayHelper {

    /**
     * Displays a numbered list of menu options with corresponding prices based on size and extra status.
     *
     * @param category the item category name for display purposes
     * @param items array of enum values to display
     * @param size the Size enum used for price calculation
     * @param isExtra whether these are additional/extra items (affects pricing)
     */
    public static <T extends Enum<T>> void displayOptionsWithPrices(String category, T[] items, Size size, boolean isExtra) {
        System.out.println("\n" + (isExtra ? "Extra " : "") + capitalize(category) + " Options:");
        for (int i = 0; i < items.length; i++) {
            BigDecimal price = getPrice(items[i], size, isExtra);
            System.out.printf("%d) %s - %s%s%n", i + 1, items[i], formatPrice(price), (isExtra ? " (Extra)" : ""));
        }
    }

    /**
     * Calculates price for menu items based on type, size, and extra status.
     * Uses predefined pricing arrays indexed by size ordinal.
     *
     * @param item the menu item enum
     * @param size the Size enum for price tier selection
     * @param isExtra whether this is an additional item (reduced pricing)
     * @return BigDecimal price for the item
     */
    private static BigDecimal getPrice(Enum<?> item, Size size, boolean isExtra) {
        int index = size.ordinal(); // Map size enum to array index

        if (item instanceof Meat) {
            // Meat pricing: regular vs extra portions
            return isExtra ? new BigDecimal[] { new BigDecimal("0.50"), new BigDecimal("1.00"), new BigDecimal("1.50") }[index]
                    : new BigDecimal[] { new BigDecimal("1.00"), new BigDecimal("2.00"), new BigDecimal("3.00") }[index];
        } else if (item instanceof Cheese) {
            // Cheese pricing: regular vs extra portions
            return isExtra ? new BigDecimal[] { new BigDecimal("0.30"), new BigDecimal("0.60"), new BigDecimal("0.90") }[index]
                    : new BigDecimal[] { new BigDecimal("0.75"), new BigDecimal("1.50"), new BigDecimal("2.25") }[index];
        } else if (item instanceof Bread) {
            // Base sandwich pricing by size
            return new BigDecimal[] { new BigDecimal("5.50"), new BigDecimal("7.00"), new BigDecimal("8.50") }[index];
        } else if (item instanceof Topping || item instanceof Sauce) {
            // Toppings and sauces are complimentary
            return BigDecimal.ZERO;
        } else if (item instanceof ChipFlavor) {
            // Chips have flat pricing regardless of flavor
            return new BigDecimal("1.50");
        } else if (item instanceof DrinkFlavor) {
            // Drink pricing by size
            return new BigDecimal[] { new BigDecimal("2.00"), new BigDecimal("2.50"), new BigDecimal("3.00") }[index];
        }
        return BigDecimal.ZERO;
    }

    /**
     * Formats price for display. Zero prices show as "Included".
     *
     * @param price the BigDecimal price to format
     * @return formatted price string
     */
    private static String formatPrice(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) == 0 ? "Included" : "$" + price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Capitalizes the first letter of a word for display formatting.
     *
     * @param word the string to capitalize
     * @return capitalized string
     */
    private static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}