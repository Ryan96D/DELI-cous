package menus;

import models.Order;

import java.util.Scanner;

public class OrderScreen {

    public static void start() {
        Scanner scanner = new Scanner(System.in);

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
                    // TODO: call Sandwich creation
                    System.out.println("Add Sandwich selected.");
                    break;
                case "2":
                    // TODO: call Drink creation
                    System.out.println("Add Drink selected.");
                    break;
                case "3":
                    // TODO: call Chips creation
                    System.out.println("Add Chips selected.");
                    break;
                case "4":
                    // TODO: call Checkout
                    System.out.println("Checkout selected.");
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