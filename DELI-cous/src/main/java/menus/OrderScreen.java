package menus;

import models.Order;
import models.items.Drinks;
import models.items.Chips;
import models.items.Sandwich;
import utils.ReceiptWriter;

import java.util.Scanner;

// Main order management screen that handles the ordering process
public class OrderScreen {

    // Entry point for the order screen - handles the main ordering loop
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        // Create new order instance to track current customer's order
        Order currentOrder = new Order();

        // Main ordering loop - continues until checkout or cancellation
        while (true) {
            // Display the main order menu options
            System.out.println("\n=== Order Screen ===");
            System.out.println("1) Add Custom Sandwich");
            System.out.println("2) Add Signature Sandwich");
            System.out.println("3) Add Drink");
            System.out.println("4) Add Chips");
            System.out.println("5) Checkout");
            System.out.println("0) Cancel Order and Return to Home");
            System.out.print("Please select an option: ");

            String input = scanner.nextLine().trim();

            // Handle menu selection based on user input
            switch (input) {
                case "1":
                    // Launch custom sandwich builder and add result to order
                    Sandwich sandwich = SandwichBuilderMenu.start();
                    if (sandwich != null) {
                        currentOrder.addSandwich(sandwich);
                        System.out.println("Custom sandwich added to order.");
                    }
                    break;
                case "2":
                    // Launch signature sandwich menu and add selection to order
                    Sandwich signatureSandwich = SignatureSandwichMenu.start();
                    if (signatureSandwich != null) {
                        currentOrder.addSandwich(signatureSandwich);
                        System.out.println("Signature sandwich added to order.");
                    }
                    break;
                case "3":
                    // Launch drink builder and add selection to order
                    Drinks drinks = DrinkBuilderMenu.start();
                    if (drinks != null) {
                        currentOrder.addDrink(drinks);
                        System.out.println("Drink added to order.");
                    }
                    break;
                case "4":
                    // Launch chips builder and add selection to order
                    Chips chips = ChipsBuilderMenu.start();
                    if (chips != null) {
                        currentOrder.addChips(chips);
                        System.out.println("Chips added to order.");
                    }
                    break;
                case "5":
                    // Check if order has any items before proceeding to checkout
                    if (currentOrder.getSandwiches().isEmpty() &&
                            currentOrder.getDrinks().isEmpty() &&
                            currentOrder.getChips().isEmpty()) {
                        System.out.println("Your order is currently empty.");
                        break;
                    }

                    // Display order summary for review
                    OrderSummaryScreen.display(currentOrder);

                    // Confirm checkout with customer
                    System.out.print("\nWould you like to proceed to checkout? (yes/no): ");
                    String confirm = scanner.nextLine().trim().toLowerCase();

                    // Process checkout if confirmed
                    if (confirm.equals("yes") || confirm.equals("y")) {
                        // Generate and save receipt for the order
                        ReceiptWriter.writeReceipt(currentOrder);

                        System.out.println("Order confirmed! Receipt saved. Thank you.");
                        return; // Exit after successful checkout
                    } else {
                        System.out.println("Returning to Order Menu.");
                    }
                    break;

                case "0":
                    // Cancel order and return to home screen
                    System.out.println("Order cancelled. Returning to Home Screen.");
                    return; // exit to HomeScreen
                default:
                    // Handle invalid menu selections
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}