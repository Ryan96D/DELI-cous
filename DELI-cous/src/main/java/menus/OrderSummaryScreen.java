package menus;

import models.Order;
import models.items.Chips;
import models.items.Drinks;
import models.items.Sandwich;

import java.text.NumberFormat;
import java.util.Locale;

public class OrderSummaryScreen {

    /**
     * Displays a formatted summary of the complete order including all items and total price.
     *
     * @param order the Order object containing all sandwiches, drinks, and chips
     */
    public static void display(Order order) {
        System.out.println("\n=== Order Summary ===");

        // Display detailed breakdown for each sandwich with numbered labels
        int sandwichNumber = 1;
        for (Sandwich sandwich : order.getSandwiches()) {
            System.out.println("\nSandwich #" + sandwichNumber++);
            System.out.println(sandwich.getReceiptLine());
            System.out.println("- Bread: " + sandwich.getBread());
            System.out.println("- Size: " + sandwich.getSize());
            System.out.println("- Meats: " + sandwich.getMeats());
            System.out.println("- Cheeses: " + sandwich.getCheeses());
            System.out.println("- Toppings: " + sandwich.getToppings());
            System.out.println("- Sauces: " + sandwich.getSauces());
            System.out.println("- Toasted: " + (sandwich.isToasted() ? "Yes" : "No"));
        }

        // Only display drinks section if drinks were ordered
        if (!order.getDrinks().isEmpty()) {
            System.out.println("\nDrinks:");
            for (Drinks drink : order.getDrinks()) {
                System.out.println("- " + drink.getReceiptLine());
            }
        }

        // Only display chips section if chips were ordered
        if (!order.getChips().isEmpty()) {
            System.out.println("\nChips:");
            for (Chips chip : order.getChips()) {
                System.out.println("- " + chip.getReceiptLine());
            }
        }

        // Format total as US currency with proper locale formatting
        String formattedTotal = NumberFormat.getCurrencyInstance(Locale.US)
                .format(order.getTotalPrice());

        System.out.println("\nTotal: " + formattedTotal);
        System.out.println("=====================");
    }
}