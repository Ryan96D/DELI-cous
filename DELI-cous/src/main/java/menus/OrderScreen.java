package menus;

import models.Order;
import models.items.Drinks;
import models.items.Chips;
import models.items.Sandwich;
import utils.ReceiptWriter;

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
                    if (currentOrder.getSandwiches().isEmpty() &&
                            currentOrder.getDrinks().isEmpty() &&
                            currentOrder.getChips().isEmpty()) {
                        System.out.println("Your order is currently empty.");
                        break;
                    }

                    OrderSummaryScreen.display(currentOrder);

                    System.out.print("\nWould you like to proceed to checkout? (yes/no): ");
                    String confirm = scanner.nextLine().trim().toLowerCase();

                    if (confirm.equals("yes") || confirm.equals("y")) {
                        ReceiptWriter.writeReceipt(currentOrder);

                        System.out.println("Order confirmed! Receipt saved. Thank you.");
                        return;
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