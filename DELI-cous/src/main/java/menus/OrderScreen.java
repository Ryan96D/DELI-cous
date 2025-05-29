package menus;

import models.Order;
import models.items.DrinkItem;
import models.items.Sandwich;
import java.util.Scanner;

public class OrderScreen {

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        Order currentOrder = new Order();  // create a new order for this session

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
                    currentOrder.addSandwich(sandwich);  // add sandwich to order
                    System.out.println("Sandwich added to order.");
                    break;
                case "2":
                    DrinkItem drink = DrinkBuilderMenu.start();
                    currentOrder.addDrink(drink);  // add drink to order
                    System.out.println("Drink added to order.");
                    break;
                case "3":
                    // TODO: call Chips creation and add to order
                    System.out.println("Add Chips selected.");
                    break;
                case "4":
                    // TODO: handle checkout, e.g., show order summary, payment, etc.
                    System.out.println("Checkout selected.");
                    // Example: currentOrder.checkout();
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
