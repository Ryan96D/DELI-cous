package menus;

import enums.Bread;
import enums.Cheese;
import enums.Meat;
import enums.Size;
import enums.Topping;
import models.items.Sandwich;

import java.util.ArrayList;
import java.util.List;
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

        // Choose Meat
        List<Meat> selectedMeats = new ArrayList<>();
        System.out.println("Select meat:");
        for (Meat meat : Meat.values()) {
            System.out.println(meat.ordinal() + 1 + ") " + meat);
        }
        System.out.print("Enter choice: ");
        int meatChoice = Integer.parseInt(scanner.nextLine()) - 1;
        selectedMeats.add(Meat.values()[meatChoice]);

        // Ask for extra meat
        System.out.print("Would you like extra meat? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            System.out.println("Select an additional meat:");
            for (Meat meat : Meat.values()) {
                System.out.println(meat.ordinal() + 1 + ") " + meat);
            }
            System.out.print("Enter choice: ");
            int extraMeatChoice = Integer.parseInt(scanner.nextLine()) - 1;
            Meat extraMeat = Meat.values()[extraMeatChoice];
            selectedMeats.add(extraMeat);
        }

        // Choose Cheese
        List<Cheese> selectedCheeses = new ArrayList<>();
        System.out.println("Select cheese:");
        for (Cheese cheese : Cheese.values()) {
            System.out.println(cheese.ordinal() + 1 + ") " + cheese);
        }
        System.out.print("Enter choice: ");
        int cheeseChoice = Integer.parseInt(scanner.nextLine()) - 1;
        selectedCheeses.add(Cheese.values()[cheeseChoice]);

        // Ask for extra cheese
        System.out.print("Would you like extra cheese? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            System.out.println("Select an additional cheese:");
            for (Cheese cheese : Cheese.values()) {
                System.out.println(cheese.ordinal() + 1 + ") " + cheese);
            }
            System.out.print("Enter choice: ");
            int extraCheeseChoice = Integer.parseInt(scanner.nextLine()) - 1;
            Cheese extraCheese = Cheese.values()[extraCheeseChoice];
            selectedCheeses.add(extraCheese);
        }

        // Choose Regular Toppings
        List<Topping> selectedToppings = new ArrayList<>();
        System.out.println("Select regular toppings (type 0 to stop):");
        while (true) {
            for (Topping topping : Topping.values()) {
                System.out.println(topping.ordinal() + 1 + ") " + topping);
            }
            System.out.print("Enter choice: ");
            String input = scanner.nextLine();
            if (input.equals("0")) break;
            int toppingChoice = Integer.parseInt(input) - 1;
            selectedToppings.add(Topping.values()[toppingChoice]);
        }

        // Toasted?
        System.out.print("Would you like the sandwich toasted? (y/n): ");
        boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("y");

        // Create Sandwich object
        Sandwich sandwich = new Sandwich(selectedBread, selectedSize);
        sandwich.setMeats(selectedMeats);
        sandwich.setCheeses(selectedCheeses);
        sandwich.setToppings(selectedToppings);
        sandwich.setToasted(toasted);

        System.out.println("Sandwich added!");

        return sandwich;
    }
}