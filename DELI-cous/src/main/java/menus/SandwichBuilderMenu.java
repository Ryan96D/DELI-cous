package menus;

import enums.*;
import models.items.Sandwich;

import java.util.*;

public class SandwichBuilderMenu {

    public static Sandwich start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Build Your Sandwich ===");

        // Bread
        Bread selectedBread = chooseOption("bread", Bread.values(), scanner);

        // Size
        Size selectedSize = chooseOption("size (4\", 8\", 12\")", Size.values(), scanner);

        // Meats
        List<Meat> selectedMeats = chooseMultiple("meat", Meat.values(), scanner, 2);

        // Cheeses
        List<Cheese> selectedCheeses = chooseMultiple("cheese", Cheese.values(), scanner, 2);

        // Toppings
        List<Topping> selectedToppings = chooseMultiple("topping", Topping.values(), scanner, 10);

        // Sauces
        List<Sauce> selectedSauces = chooseMultiple("sauce", Sauce.values(), scanner, 5);

        // Toasted
        System.out.print("Would you like the sandwich toasted? (y/n): ");
        boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("y");

        // Create and return the Sandwich
        Sandwich sandwich = new Sandwich(selectedBread, selectedSize);
        sandwich.setMeats(selectedMeats);
        sandwich.setCheeses(selectedCheeses);
        sandwich.setToppings(selectedToppings);
        sandwich.setSauces(selectedSauces);
        sandwich.setToasted(toasted);

        System.out.println("\nSandwich added!");
        return sandwich;
    }

    private static <T extends Enum<T>> T chooseOption(String category, T[] options, Scanner scanner) {
        while (true) {
            System.out.println("Select your " + category + ":");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.print("Enter choice: ");
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= options.length) {
                    return options[choice - 1];
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid input. Please try again.");
        }
    }

    private static <T extends Enum<T>> List<T> chooseMultiple(String category, T[] options, Scanner scanner, int max) {
        List<T> selected = new ArrayList<>();
        while (selected.size() < max) {
            System.out.println("\nCurrent " + category + "s: " + selected);
            System.out.println("Select " + category + " (0 to finish, R to remove last, B to go back):");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.print("Choice: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("0")) break;
            if (input.equalsIgnoreCase("R")) {
                if (!selected.isEmpty()) {
                    T removed = selected.remove(selected.size() - 1);
                    System.out.println("Removed: " + removed);
                } else {
                    System.out.println("Nothing to remove.");
                }
                continue;
            }
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= options.length) {
                    selected.add(options[choice - 1]);
                    continue;
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid input. Please try again.");
        }
        return selected;
    }
}
