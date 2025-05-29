
package menus;

import enums.*;
import models.items.Sandwich;
import java.util.*;

public class SignatureSandwichEditorMenu {

    public static Sandwich editSandwich(Sandwich preset) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Edit Signature Sandwich ===");
        displayCurrentSandwich(preset);

        // Store components safely with proper types
        Bread selectedBread = preset.getBread();
        Size selectedSize = preset.getSize();
        List<Meat> selectedMeats = new ArrayList<>(preset.getMeats());
        List<Cheese> selectedCheeses = new ArrayList<>(preset.getCheeses());
        List<Topping> selectedToppings = new ArrayList<>(preset.getToppings());
        List<Sauce> selectedSauces = new ArrayList<>(preset.getSauces());
        boolean toasted = preset.isToasted();

        while (true) {
            System.out.println("\nWhat would you like to edit?");
            System.out.println("1) Bread (current: " + selectedBread + ")");
            System.out.println("2) Size (current: " + selectedSize + ")");
            System.out.println("3) Meats (current: " + selectedMeats + ")");
            System.out.println("4) Cheeses (current: " + selectedCheeses + ")");
            System.out.println("5) Toppings (current: " + selectedToppings + ")");
            System.out.println("6) Sauces (current: " + selectedSauces + ")");
            System.out.println("7) Toasted (current: " + toasted + ")");
            System.out.println("0) Done");
            System.out.print("Choice: ");

            String input = scanner.nextLine().trim();

            try {
                switch (input) {
                    case "0":
                        return buildSandwich(selectedBread, selectedSize, selectedMeats,
                                selectedCheeses, selectedToppings, selectedSauces, toasted);
                    case "1":
                        Bread newBread = editSingle("bread", Bread.values(), scanner);
                        if (newBread != null) selectedBread = newBread;
                        break;
                    case "2":
                        Size newSize = editSingle("size", Size.values(), scanner);
                        if (newSize != null) selectedSize = newSize;
                        break;
                    case "3":
                        List<Meat> newMeats = editList("meat", Meat.values(), selectedMeats, scanner, true);
                        if (newMeats != null) selectedMeats = newMeats;
                        break;
                    case "4":
                        List<Cheese> newCheeses = editList("cheese", Cheese.values(), selectedCheeses, scanner, true);
                        if (newCheeses != null) selectedCheeses = newCheeses;
                        break;
                    case "5":
                        List<Topping> newToppings = editList("topping", Topping.values(), selectedToppings, scanner, false);
                        if (newToppings != null) selectedToppings = newToppings;
                        break;
                    case "6":
                        List<Sauce> newSauces = editList("sauce", Sauce.values(), selectedSauces, scanner, false);
                        if (newSauces != null) selectedSauces = newSauces;
                        break;
                    case "7":
                        Boolean newToasted = editBoolean("toasted", toasted, scanner);
                        if (newToasted != null) toasted = newToasted;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                e.printStackTrace(); // This will help us see what's going wrong
            }
        }
    }

    private static <T extends Enum<T>> T editSingle(String name, T[] options, Scanner scanner) {
        try {
            System.out.println("\nSelect " + name + ":");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.print("Choice (0 to cancel): ");

            String input = scanner.nextLine().trim();
            if (input.equals("0")) return null;

            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= options.length) {
                return options[choice - 1];
            } else {
                System.out.println("Invalid choice, keeping current selection.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, keeping current selection.");
            return null;
        }
    }

    private static <T extends Enum<T>> List<T> editList(String name, T[] options, List<T> current, Scanner scanner, boolean requireOne) {
        try {
            List<T> selected = new ArrayList<>(current);

            while (true) {
                System.out.println("\nCurrent " + name + "s: " + selected);
                System.out.println("1) Add " + name + " 2) Remove " + name + " 0) Done");
                System.out.print("Choice: ");

                String input = scanner.nextLine().trim();

                if (input.equals("0")) {
                    if (requireOne && selected.isEmpty()) {
                        System.out.println("Must have at least one " + name + ".");
                        continue;
                    }
                    return selected;
                } else if (input.equals("1")) {
                    System.out.println("Add " + name + ":");
                    for (int i = 0; i < options.length; i++) {
                        System.out.println((i + 1) + ") " + options[i]);
                    }
                    System.out.print("Choice: ");
                    try {
                        int choice = Integer.parseInt(scanner.nextLine().trim());
                        if (choice >= 1 && choice <= options.length) {
                            T item = options[choice - 1];
                            if (!selected.contains(item)) {
                                selected.add(item);
                                System.out.println("Added: " + item);
                            } else {
                                System.out.println("Already selected.");
                            }
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                    }
                } else if (input.equals("2")) {
                    if (selected.isEmpty()) {
                        System.out.println("Nothing to remove.");
                    } else {
                        System.out.println("Remove " + name + ":");
                        for (int i = 0; i < selected.size(); i++) {
                            System.out.println((i + 1) + ") " + selected.get(i));
                        }
                        System.out.print("Choice: ");
                        try {
                            int choice = Integer.parseInt(scanner.nextLine().trim());
                            if (choice >= 1 && choice <= selected.size()) {
                                T removed = selected.remove(choice - 1);
                                System.out.println("Removed: " + removed);
                            } else {
                                System.out.println("Invalid choice.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error editing " + name + " list. Keeping current selection.");
            e.printStackTrace();
            return current;
        }
    }

    private static Boolean editBoolean(String name, Boolean current, Scanner scanner) {
        try {
            System.out.print("Make sandwich " + name + "? (y/n, enter to keep current): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            return current; // Keep current if just pressed enter
        } catch (Exception e) {
            System.out.println("Error editing " + name + ". Keeping current value.");
            return current;
        }
    }

    private static void displayCurrentSandwich(Sandwich sandwich) {
        System.out.println("Current configuration:");
        System.out.println("  Bread: " + sandwich.getBread());
        System.out.println("  Size: " + sandwich.getSize());
        System.out.println("  Meats: " + sandwich.getMeats());
        System.out.println("  Cheeses: " + sandwich.getCheeses());
        System.out.println("  Toppings: " + sandwich.getToppings());
        System.out.println("  Sauces: " + sandwich.getSauces());
        System.out.println("  Toasted: " + sandwich.isToasted());
    }

    private static Sandwich buildSandwich(Bread bread, Size size, List<Meat> meats,
                                          List<Cheese> cheeses, List<Topping> toppings,
                                          List<Sauce> sauces, boolean toasted) {
        try {
            Sandwich sandwich = new Sandwich(bread, size);
            sandwich.setMeats(new ArrayList<>(meats));
            sandwich.setCheeses(new ArrayList<>(cheeses));
            sandwich.setToppings(new ArrayList<>(toppings));
            sandwich.setSauces(new ArrayList<>(sauces));
            sandwich.setToasted(toasted);

            System.out.println("\nSandwich updated!");
            return sandwich;
        } catch (Exception e) {
            System.out.println("Error creating sandwich: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}