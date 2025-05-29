package menus;

import enums.DrinkFlavor;
import enums.Size;
import models.items.Drinks;

import java.util.Scanner;

public class DrinkBuilderMenu {

    public static Drinks start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Add Drink ===");

        // Select Drink
        System.out.println("Select your drink:");
        for (DrinkFlavor drinkFlavor : DrinkFlavor.values()) {
            System.out.println(drinkFlavor.ordinal() + 1 + ") " + drinkFlavor);
        }
        System.out.print("Enter choice: ");
        int drinkChoice = Integer.parseInt(scanner.nextLine()) - 1;
        DrinkFlavor selectedDrinkFlavor = DrinkFlavor.values()[drinkChoice];

        // Select Size
        System.out.println("Select drink size: (12 oz, 16 oz, 24 oz)");
        for (Size size : Size.values()) {
            System.out.println(size.ordinal() + 1 + ") " + size);
        }
        System.out.print("Enter choice: ");
        int sizeChoice = Integer.parseInt(scanner.nextLine()) - 1;
        Size selectedSize = Size.values()[sizeChoice];

        Drinks drinks = new Drinks(selectedDrinkFlavor, selectedSize);

        System.out.println("Drink added: " + drinks);

        return drinks;
    }
}