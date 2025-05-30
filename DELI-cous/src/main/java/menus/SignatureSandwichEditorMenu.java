package menus;

import enums.*;
import models.items.Sandwich;
import java.util.*;

// Editor menu for customizing signature sandwiches after selecting a preset
public class SignatureSandwichEditorMenu {

    // Main editing interface - allows modification of any component of a signature sandwich
    public static Sandwich editSandwich(Sandwich preset) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Edit Signature Sandwich ===");
        displayCurrentSandwich(preset);

        // Create copies of all sandwich components to allow safe editing
        Bread selectedBread = preset.getBread();
        Size selectedSize = preset.getSize();
        List<Meat> selectedMeats = new ArrayList<>(preset.getMeats());
        List<Cheese> selectedCheeses = new ArrayList<>(preset.getCheeses());
        List<Topping> selectedToppings = new ArrayList<>(preset.getToppings());
        List<Sauce> selectedSauces = new ArrayList<>(preset.getSauces());
        boolean toasted = preset.isToasted();

        // Main editing loop - continue until customer is done customizing
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

            // Handle each editing option with error handling
            try {
                switch (input) {
                    case "0":
                        // Build and return the final customized sandwich
                        return buildSandwich(selectedBread, selectedSize, selectedMeats,
                                selectedCheeses, selectedToppings, selectedSauces, toasted);
                    case "1":
                        // Edit bread type
                        Bread newBread = editSingle("bread", Bread.values(), scanner);
                        if (newBread != null) selectedBread = newBread;
                        break;
                    case "2":
                        // Edit sandwich size
                        Size newSize = editSingle("size", Size.values(), scanner);
                        if (newSize != null) selectedSize = newSize;
                        break;
                    case "3":
                        // Edit meat selection - at least one required
                        List<Meat> newMeats = editList("meat", Meat.values(), selectedMeats, scanner, true);
                        if (newMeats != null) selectedMeats = newMeats;
                        break;
                    case "4":
                        // Edit cheese selection - at least one required
                        List<Cheese> newCheeses = editList("cheese", Cheese.values(), selectedCheeses, scanner, true);
                        if (newCheeses != null) selectedCheeses = newCheeses;
                        break;
                    case "5":
                        // Edit toppings - optional
                        List<Topping> newToppings = editList("topping", Topping.values(), selectedToppings, scanner, false);
                        if (newToppings != null) selectedToppings = newToppings;
                        break;
                    case "6":
                        // Edit sauces - optional
                        List<Sauce> newSauces = editList("sauce", Sauce.values(), selectedSauces, scanner, false);
                        if (newSauces != null) selectedSauces = newSauces;
                        break;
                    case "7":
                        // Toggle toasted option
                        Boolean newToasted = editBoolean("toasted", toasted, scanner);
                        if (newToasted != null) toasted = newToasted;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                e.printStackTrace(); // Debug output for troubleshooting
            }
        }
    }

    // Helper method for editing single-choice components (bread, size)
    private static <T extends Enum<T>> T editSingle(String name, T[] options, Scanner scanner) {
        try {
            System.out.println("\nSelect " + name + ":");
            // Display all available options with numbers
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.print("Choice (0 to cancel): ");

            String input = scanner.nextLine().trim();
            if (input.equals("0")) return null; // Cancel - keep current selection

            // Parse and validate input
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

    // Helper method for editing list components (meats, cheeses, toppings, sauces)
    private static <T extends Enum<T>> List<T> editList(String name, T[] options, List<T> current, Scanner scanner, boolean requireOne) {
        try {
            // Work with a copy to avoid modifying original until confirmed
            List<T> selected = new ArrayList<>(current);

            while (true) {
                System.out.println("\nCurrent " + name + "s: " + selected);
                System.out.println("1) Add " + name + " 2) Remove " + name + " 0) Done");
                System.out.print("Choice: ");

                String input = scanner.nextLine().trim();

                if (input.equals("0")) {
                    // Check if requirement is met before finishing
                    if (requireOne && selected.isEmpty()) {
                        System.out.println("Must have at least one " + name + ".");
                        continue;
                    }
                    return selected;
                } else if (input.equals("1")) {
                    // Add new item to selection
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
                    // Remove item from selection
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
            return current; // Return original if error occurs
        }
    }

    // Helper method for editing boolean options (toasted)
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

    // Display the current sandwich configuration
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

    // Build the final sandwich object with all selected components
    private static Sandwich buildSandwich(Bread bread, Size size, List<Meat> meats,
                                          List<Cheese> cheeses, List<Topping> toppings,
                                          List<Sauce> sauces, boolean toasted) {
        try {
            Sandwich sandwich = new Sandwich(bread, size);
            // Create new lists to avoid reference issues
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