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
                    System.out.println("Checkout selected.");
                    // TODO: implement checkout
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