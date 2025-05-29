package utils;

import enums.Cheese;
import enums.Meat;
import java.math.BigDecimal;

public class MenuDisplayHelper {

    public static void displayMeatOptions(Meat[] meats, boolean isExtra) {
        System.out.println((isExtra ? "Extra Meats (additional charge applies):" : "Meat Options:"));
        for (int i = 0; i < meats.length; i++) {
            BigDecimal price = isExtra ? meats[i].getExtraPrice() : meats[i].getPrice();
            System.out.println((i + 1) + ") " + meats[i] + " - " + formatPrice(price) + (isExtra ? " (Extra)" : ""));
        }
    }

    public static void displayCheeseOptions(Cheese[] cheeses, boolean isExtra) {
        System.out.println((isExtra ? "Extra Cheeses (additional charge applies):" : "Cheese Options:"));
        for (int i = 0; i < cheeses.length; i++) {
            BigDecimal price = isExtra ? cheeses[i].getExtraPrice() : cheeses[i].getPrice();
            System.out.println((i + 1) + ") " + cheeses[i] + " - " + formatPrice(price) + (isExtra ? " (Extra)" : ""));
        }
    }

    private static String formatPrice(BigDecimal price) {
        return "$" + price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}