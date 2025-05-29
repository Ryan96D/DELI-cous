package menus;

import models.items.Sandwich;
import presets.*;
import java.util.Scanner;

public class SignatureSandwichMenu {

    public static Sandwich start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Signature Sandwiches ===");
            System.out.println("1) BLT");
            System.out.println("2) Philly Cheese Steak");
            System.out.println("3) Back to main menu");
            System.out.print("Choose a signature sandwich to customize: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    BLT bltPreset = new BLT();
                    displayPreset("BLT", bltPreset);
                    return SignatureSandwichEditorMenu.editSandwich(bltPreset);

                case "2":
                    PhillyCheeseSteak phillyPreset = new PhillyCheeseSteak();
                    displayPreset("Philly Cheese Steak", phillyPreset);
                    return SignatureSandwichEditorMenu.editSandwich(phillyPreset);

                case "3":
                    return null; // Signal to go back

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

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