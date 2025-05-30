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
            System.out.println("3) Italian BMT");
            System.out.println("4) Turkey Club");
            System.out.println("5) Veggie Deluxe");
            System.out.println("6) Wonder Bread Deluxe");
            System.out.println("7) Back to main menu");
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
                    // Create Italian BMT preset with default ingredients
                    ItalianBMT italianPreset = new ItalianBMT();
                    displayPreset("Italian BMT", italianPreset);
                    // Pass to editor for customization before returning
                    return SignatureSandwichEditorMenu.editSandwich(italianPreset);

                case "4":
                    // Create Turkey Club preset with default ingredients
                    TurkeyClub turkeyPreset = new TurkeyClub();
                    displayPreset("Turkey Club", turkeyPreset);
                    // Pass to editor for customization before returning
                    return SignatureSandwichEditorMenu.editSandwich(turkeyPreset);

                case "5":
                    // Create Veggie Deluxe preset with default ingredients
                    VeggieDeluxe veggiePreset = new VeggieDeluxe();
                    displayPreset("Veggie Deluxe", veggiePreset);
                    // Pass to editor for customization before returning
                    return SignatureSandwichEditorMenu.editSandwich(veggiePreset);

                case "6":
                    // Create Veggie Deluxe preset with default ingredients
                    WonderBreadDeluxe wonderBreadPreset = new WonderBreadDeluxe();
                    displayPreset("Wonder Bread Deluxe",wonderBreadPreset);
                    // Pass to editor for customization before returning
                    return  SignatureSandwichEditorMenu.editSandwich(wonderBreadPreset);

                case "7":
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