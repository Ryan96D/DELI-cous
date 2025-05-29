package menus;

import enums.*;
import models.items.Sandwich;
import utils.MenuDisplayHelper;

import java.util.*;

public class SandwichBuilderMenu {

    public static Sandwich start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Build Your Sandwich ===");

        Bread selectedBread = chooseOption("bread", Bread.values(), scanner);
        Size selectedSize = chooseOption("size (4\", 8\", 12\")", Size.values(), scanner);

        List<Meat> selectedMeats = chooseMultiple("meat", Meat.values(), scanner, 2, true);
        List<Cheese> selectedCheeses = chooseMultiple("cheese", Cheese.values(), scanner, 2, true);
        List<Topping> selectedToppings = chooseMultiple("topping", Topping.values(), scanner, 10, false);
        List<Sauce> selectedSauces = chooseMultiple("sauce", Sauce.values(), scanner, 5, false);

        System.out.print("Would you like the sandwich toasted? (y/n): ");
        boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("y");

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

    private static <T extends Enum<T>> List<T> chooseMultiple(String category, T[] options, Scanner scanner, int max, boolean showPrices) {
        List<T> selected = new ArrayList<>();
        boolean isExtra = false;

        while (selected.size() < max) {
            System.out.println("\nCurrent " + category + "s: " + formatSelected(selected, category));
            if (showPrices && category.equals("meat")) MenuDisplayHelper.displayMeatOptions((Meat[]) options, isExtra);
            else if (showPrices && category.equals("cheese")) MenuDisplayHelper.displayCheeseOptions((Cheese[]) options, isExtra);
            else {
                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ") " + options[i]);
                }
            }

            System.out.println("Select " + category + " (0 to finish, R to remove last, B to go back):");
            System.out.print("Choice: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("0")) {
                if (!isExtra && selected.size() > 0 && max == 2) {
                    System.out.print("Would you like extra " + category + "? (y/n): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                        isExtra = true;
                        continue;
                    }
                }
                break;
            }

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
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException ignored) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return selected;
    }

    private static <T> String formatSelected(List<T> items, String category) {
        if (items.isEmpty()) return "None";
        StringBuilder sb = new StringBuilder();
        for (T item : items) {
            sb.append(item);
            if (category.equals("meat") || category.equals("cheese")) {
                if (Collections.frequency(items, item) > 1) sb.append(" (Extra)");
            }
            sb.append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}
