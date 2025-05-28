package menus;

import enums.Bread;
import enums.Size;
import models.items.Sandwich;

import java.util.Scanner;

public class SandwichBuilderMenu {

    public static Sandwich start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Add Sandwich ===");

        // Choose Bread
        System.out.println("Select your bread:");
        for (Bread bread : Bread.values()) {
            System.out.println(bread.ordinal() + 1 + ") " + bread);
        }
        System.out.print("Enter choice: ");
        int breadChoice = Integer.parseInt(scanner.nextLine()) - 1;
        Bread selectedBread = Bread.values()[breadChoice];

        // Choose Size
        System.out.println("Select sandwich size:");
        for (Size size : Size.values()) {
            System.out.println(size.ordinal() + 1 + ") " + size);
        }
        System.out.print("Enter choice: ");
        int sizeChoice = Integer.parseInt(scanner.nextLine()) - 1;
        Size selectedSize = Size.values()[sizeChoice];

        // TODO: Walk through toppings, sauces, extras...

        // Toasted?
        System.out.print("Would you like the sandwich toasted? (y/n): ");
        boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("y");

        // Create Sandwich object
        Sandwich sandwich = new Sandwich(selectedBread, selectedSize);
        sandwich.setToasted(toasted);

        // TODO: Set toppings and sauces on sandwich here

        System.out.println("Sandwich added!");

        return sandwich;
    }
}