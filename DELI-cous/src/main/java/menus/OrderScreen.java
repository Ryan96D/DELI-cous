package menus;

import models.Order;
import models.items.Drinks;
import models.items.Chips;
import models.items.Sandwich;

import java.util.Scanner;

public class OrderScreen {

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        Order currentOrder = new Order();

        while (true) {
            System.out.println("\n=== Order Screen ===");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order and Return to Home");
            System.out.print("Please select an option: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    Sandwich sandwich = SandwichBuilderMenu.start();
                    if (sandwich != null) {
                        currentOrder.addSandwich(sandwich);
                        System.out.println("Sandwich added to order.");
                    }
                    break;
                case "2":
                    Drinks drinks = DrinkBuilderMenu.start();
                    if (drinks != null) {
                        currentOrder.addDrink(drinks);
                        System.out.println("Drink added to order.");
                    }
                    break;
                case "3":
                    Chips chips = ChipsBuilderMenu.start();
                    if (chips != null) {
                        currentOrder.addChips(chips);
                        System.out.println("Chips added to order.");
                    }
                    break;
                case "4":
                    System.out.println("\n=== Order Summary ===");

                    if (currentOrder.getSandwiches().isEmpty() && currentOrder.getDrinks().isEmpty() && currentOrder.getChips().isEmpty()) {
                        System.out.println("Your order is currently empty.");
                        break;
                    }

                    if (!currentOrder.getSandwiches().isEmpty()) {
                        System.out.println("\nSandwiches:");
                        for (int i = 0; i < currentOrder.getSandwiches().size(); i++) {
                            System.out.println((i + 1) + ") " + currentOrder.getSandwiches().get(i).getReceiptLine());
                        }
                    }

                    if (!currentOrder.getDrinks().isEmpty()) {
                        System.out.println("\nDrinks:");
                        for (int i = 0; i < currentOrder.getDrinks().size(); i++) {
                            System.out.println((i + 1) + ") " + currentOrder.getDrinks().get(i).getReceiptLine());
                        }
                    }

                    if (!currentOrder.getChips().isEmpty()) {
                        System.out.println("\nChips:");
                        for (int i = 0; i < currentOrder.getChips().size(); i++) {
                            System.out.println((i + 1) + ") " + currentOrder.getChips().get(i).getReceiptLine());
                        }
                    }

                    System.out.println("\nTotal: $" + currentOrder.getTotalPrice());

                    System.out.print("\nWould you like to proceed to checkout? (yes/no): ");
                    String confirm = scanner.nextLine().trim().toLowerCase();

                    if (confirm.equals("yes") || confirm.equals("y")) {
                        System.out.println("Order confirmed! Thank you.");
                        // TODO: send to receipt printer or finalize order
                        return; // exits
                    } else {
                        System.out.println("Returning to Order Menu.");
                    }
                    break;
                case "0":
                    System.out.println("Order cancelled. Returning to Home Screen.");
                    return; // exit to HomeScreen
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}