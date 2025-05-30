package menus;

import models.items.Sandwich;
import presets.*;
import java.util.Scanner;

// Menu for selecting and customizing pre-configured signature sandwiches
public class SignatureSandwichMenu {

    // Entry point for signature sandwich selection - returns customized sandwich or null if cancelled
    public static Sandwich start() {
        Scanner scanner = new Scanner(System.in);

        // Keep showing menu until valid selection or user goes back
        while (true) {
            System.out.println("\n=== Signature Sandwiches ===");
            System.out.println("1) BLT");
            System.out.println("2) Philly Cheese Steak");
            System.out.println("3) Back to main menu");
            System.out.print("Choose a signature sandwich to customize: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // Create BLT preset with default ingredients
                    BLT bltPreset = new BLT();
                    displayPreset("BLT", bltPreset);
                    // Pass to editor for customization before returning
                    return SignatureSandwichEditorMenu.editSandwich(bltPreset);

                case "2":
                    // Create Philly Cheese Steak preset with default ingredients
                    PhillyCheeseSteak phillyPreset = new PhillyCheeseSteak();
                    displayPreset("Philly Cheese Steak", phillyPreset);
                    // Pass to editor for customization before returning
                    return SignatureSandwichEditorMenu.editSandwich(phillyPreset);

                case "3":
                    return null; // Signal to go back

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Display the default configuration of a signature sandwich before customization
    private static void displayPreset(String name, Sandwich sandwich) {
        System.out.println("\n" + name + " includes:");
        System.out.println("  Bread: " + sandwich.getBread());
        System.out.println("  Size: " + sandwich.getSize());
        System.out.println("  Meats: " + sandwich.getMeats());
        System.out.println("  Cheeses: " + sandwich.getCheeses());
        System.out.println("  Toppings: " + sandwich.getToppings());
        System.out.println("  Sauces: " + sandwich.getSauces());
        System.out.println("  Toasted: " + (sandwich.isToasted() ? "Yes" : "No"));
        System.out.println("\nYou can customize any of these options...");
    }
}