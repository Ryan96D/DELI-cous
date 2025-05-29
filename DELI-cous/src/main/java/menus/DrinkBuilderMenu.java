package menus;

import enums.DrinkFlavor;
import enums.Size;
import models.items.Drinks;

import java.util.Scanner;

public class DrinkBuilderMenu {

    public static Drinks start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Add Drink ===");

        // Select Drink - show all flavors with each size and price
        DrinkFlavor selectedDrinkFlavor = null;
        Size selectedSize = null;

        while (selectedDrinkFlavor == null) {
            System.out.println("Select your drink flavor:");
            for (int i = 0; i < DrinkFlavor.values().length; i++) {
                System.out.println((i + 1) + ") " + DrinkFlavor.values()[i]);
            }
            System.out.print("Enter choice: ");

            try {
                int drinkChoice = Integer.parseInt(scanner.nextLine()) - 1;
                if (drinkChoice >= 0 && drinkChoice < DrinkFlavor.values().length) {
                    selectedDrinkFlavor = DrinkFlavor.values()[drinkChoice];
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Select Size with prices
        while (selectedSize == null) {
            System.out.println("\nSelect drink size (12 oz, 16 oz, 24 oz):");
            // Show sizes with prices for the selected drink flavor
            for (int i = 0; i < Size.values().length; i++) {
                Size size = Size.values()[i];
                String sizeLabel = "";
                switch (size) {
                    case SMALL: sizeLabel = "12 oz"; break;
                    case MEDIUM: sizeLabel = "16 oz"; break;
                    case LARGE: sizeLabel = "24 oz"; break;
                }

                // Get price for this size
                String price = "";
                switch (size) {
                    case SMALL: price = "$2.00"; break;
                    case MEDIUM: price = "$2.50"; break;
                    case LARGE: price = "$3.00"; break;
                }

                System.out.println((i + 1) + ") " + size + " (" + sizeLabel + ") - " + price);
            }
            System.out.print("Enter choice: ");

            try {
                int sizeChoice = Integer.parseInt(scanner.nextLine()) - 1;
                if (sizeChoice >= 0 && sizeChoice < Size.values().length) {
                    selectedSize = Size.values()[sizeChoice];
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        Drinks drinks = new Drinks(selectedDrinkFlavor, selectedSize);

        System.out.println("Drink added: " + drinks);

        return drinks;
    }
}