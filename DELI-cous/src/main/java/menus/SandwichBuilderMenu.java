package menus;

import enums.*;
import models.items.Sandwich;
import utils.MenuDisplayHelper;

import java.util.*;

// Interactive sandwich builder menu that walks customers through each customization step
public class SandwichBuilderMenu {

    // Enum to track which step of the sandwich building process we're currently on
    private enum Step {
        BREAD, SIZE, MEAT, EXTRA_MEAT_PROMPT, EXTRA_MEAT, CHEESE, EXTRA_CHEESE_PROMPT, EXTRA_CHEESE, TOPPINGS, SAUCES, TOASTED, COMPLETE
    }

    // Main entry point for the sandwich builder - returns completed sandwich or null if cancelled
    public static Sandwich start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Build Your Sandwich ===");

        // Initialize all sandwich components to track selections
        Bread selectedBread = null;
        Size selectedSize = null;
        List<Meat> selectedMeats = new ArrayList<>();
        List<Cheese> selectedCheeses = new ArrayList<>();
        List<Topping> selectedToppings = new ArrayList<>();
        List<Sauce> selectedSauces = new ArrayList<>();
        boolean toasted = false;
        boolean wantsExtraMeat = false;
        boolean wantsExtraCheese = false;

        // Start with bread selection
        Step currentStep = Step.BREAD;

        // Main building loop - continues until sandwich is complete
        while (currentStep != Step.COMPLETE) {
            switch (currentStep) {
                case BREAD:
                    // Let customer choose bread type
                    selectedBread = chooseOption("bread", Bread.values(), scanner);
                    if (selectedBread != null) {
                        currentStep = Step.SIZE;
                    }
                    break;

                case SIZE:
                    // Let customer choose sandwich size - affects pricing
                    selectedSize = chooseOption("size (4\", 8\", 12\")", Size.values(), scanner);
                    if (selectedSize != null) {
                        currentStep = Step.MEAT;
                    } else {
                        currentStep = Step.BREAD; // Go back to bread selection
                    }
                    break;

                case MEAT:
                    // Meat selection is required - at least one must be chosen
                    List<Meat> meats = chooseMultiple("meat", Meat.values(), scanner, 1, selectedSize, false);
                    if (meats == null) { // User pressed B to go back
                        currentStep = Step.SIZE;
                    } else if (meats.isEmpty()) {
                        System.out.println("You must select at least one meat.");
                        // Stay on same step - meat is required
                    } else {
                        selectedMeats = new ArrayList<>(meats);
                        currentStep = Step.EXTRA_MEAT_PROMPT;
                    }
                    break;

                case EXTRA_MEAT_PROMPT:
                    // Ask if customer wants extra meat (additional charge)
                    Boolean extraMeatChoice = promptYesNoOrBack("Would you like extra meat?", scanner);
                    if (extraMeatChoice == null) { // User pressed B to go back
                        currentStep = Step.MEAT;
                    } else {
                        wantsExtraMeat = extraMeatChoice;
                        currentStep = wantsExtraMeat ? Step.EXTRA_MEAT : Step.CHEESE;
                    }
                    break;

                case EXTRA_MEAT:
                    // Let customer select additional meat if they want it
                    List<Meat> extraMeats = chooseMultiple("extra meat", Meat.values(), scanner, 1, selectedSize, true);
                    if (extraMeats == null) { // User pressed B to go back
                        currentStep = Step.EXTRA_MEAT_PROMPT;
                    } else {
                        selectedMeats.addAll(extraMeats); // Add to existing meat selection
                        currentStep = Step.CHEESE;
                    }
                    break;

                case CHEESE:
                    // Cheese selection is required - at least one must be chosen
                    List<Cheese> cheeses = chooseMultiple("cheese", Cheese.values(), scanner, 1, selectedSize, false);
                    if (cheeses == null) { // User pressed B to go back
                        currentStep = wantsExtraMeat ? Step.EXTRA_MEAT : Step.EXTRA_MEAT_PROMPT;
                    } else if (cheeses.isEmpty()) {
                        System.out.println("You must select at least one cheese.");
                        // Stay on same step - cheese is required
                    } else {
                        selectedCheeses = new ArrayList<>(cheeses);
                        currentStep = Step.EXTRA_CHEESE_PROMPT;
                    }
                    break;

                case EXTRA_CHEESE_PROMPT:
                    // Ask if customer wants extra cheese (additional charge)
                    Boolean extraCheeseChoice = promptYesNoOrBack("Would you like extra cheese?", scanner);
                    if (extraCheeseChoice == null) { // User pressed B to go back
                        currentStep = Step.CHEESE;
                    } else {
                        wantsExtraCheese = extraCheeseChoice;
                        currentStep = wantsExtraCheese ? Step.EXTRA_CHEESE : Step.TOPPINGS;
                    }
                    break;

                case EXTRA_CHEESE:
                    // Let customer select additional cheese if they want it
                    List<Cheese> extraCheeses = chooseMultiple("extra cheese", Cheese.values(), scanner, 1, selectedSize, true);
                    if (extraCheeses == null) { // User pressed B to go back
                        currentStep = Step.EXTRA_CHEESE_PROMPT;
                    } else {
                        selectedCheeses.addAll(extraCheeses); // Add to existing cheese selection
                        currentStep = Step.TOPPINGS;
                    }
                    break;

                case TOPPINGS:
                    // Toppings are optional - up to 10 allowed
                    List<Topping> toppings = chooseMultiple("topping", Topping.values(), scanner, 10, selectedSize, false);
                    if (toppings == null) { // User pressed B to go back
                        currentStep = wantsExtraCheese ? Step.EXTRA_CHEESE : Step.EXTRA_CHEESE_PROMPT;
                    } else {
                        selectedToppings = toppings;
                        currentStep = Step.SAUCES;
                    }
                    break;

                case SAUCES:
                    // Sauces are optional - up to 5 allowed
                    List<Sauce> sauces = chooseMultiple("sauce", Sauce.values(), scanner, 5, selectedSize, false);
                    if (sauces == null) { // User pressed B to go back
                        currentStep = Step.TOPPINGS;
                    } else {
                        selectedSauces = sauces;
                        currentStep = Step.TOASTED;
                    }
                    break;

                case TOASTED:
                    // Final step - ask if sandwich should be toasted
                    Boolean toastedChoice = promptYesNoOrBack("Would you like the sandwich toasted?", scanner);
                    if (toastedChoice == null) { // User pressed B to go back
                        currentStep = Step.SAUCES;
                    } else {
                        toasted = toastedChoice;
                        currentStep = Step.COMPLETE; // All steps done
                    }
                    break;
            }
        }

        // Build the final sandwich object with all selected components
        Sandwich sandwich = new Sandwich(selectedBread, selectedSize);
        sandwich.setMeats(selectedMeats);
        sandwich.setCheeses(selectedCheeses);
        sandwich.setToppings(selectedToppings);
        sandwich.setSauces(selectedSauces);
        sandwich.setToasted(toasted);

        System.out.println("\nSandwich added!");
        return sandwich;
    }

    // Helper method for single-choice selections (bread, size)
    private static <T extends Enum<T>> T chooseOption(String category, T[] options, Scanner scanner) {
        while (true) {
            System.out.println("Select your " + category + ":");
            // Display numbered list of options
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.print("Enter choice (B to go back): ");
            String input = scanner.nextLine().trim();

            // Handle back navigation
            if (input.equalsIgnoreCase("B")) {
                return null; // Signal to go back
            }

            // Validate and process numeric input
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= options.length) {
                    return options[choice - 1];
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid input. Please try again.");
        }
    }

    // Helper method for multiple-choice selections (meats, cheeses, toppings, sauces)
    private static <T extends Enum<T>> List<T> chooseMultiple(String category, T[] options, Scanner scanner, int max, Size size, boolean isExtra) {
        List<T> selected = new ArrayList<>();
        // Determine if at least one selection is required (meat and cheese are required)
        boolean requireAtLeastOne = (category.equals("meat") || category.equals("cheese")) && !isExtra;

        while (true) {
            // Show current selections and available options
            System.out.println("\nCurrent " + category + "s: " + selected);
            System.out.println("Select " + category + " (0 to finish, B to go back, R to remove):");
            MenuDisplayHelper.displayOptionsWithPrices(category, options, size, isExtra);
            System.out.print("Choice: ");
            String input = scanner.nextLine().trim();

            // Handle finish selection
            if (input.equalsIgnoreCase("0")) {
                if (requireAtLeastOne && selected.isEmpty()) {
                    System.out.println("You must select at least one " + category + ".");
                    continue;
                }
                break;
            }

            // Handle back navigation
            if (input.equalsIgnoreCase("B")) {
                return null; // Signal to go back
            }

            // Handle item removal
            if (input.equalsIgnoreCase("R")) {
                if (selected.isEmpty()) {
                    System.out.println("Nothing to remove.");
                } else {
                    // Show items that can be removed
                    System.out.println("Select item to remove:");
                    for (int i = 0; i < selected.size(); i++) {
                        System.out.println((i + 1) + ") " + selected.get(i));
                    }
                    System.out.print("Enter number: ");
                    String removeInput = scanner.nextLine().trim();
                    try {
                        int index = Integer.parseInt(removeInput) - 1;
                        if (index >= 0 && index < selected.size()) {
                            T removed = selected.remove(index);
                            System.out.println("Removed: " + removed);
                        } else {
                            System.out.println("Invalid index.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                    }
                }
                continue;
            }

            // Handle adding new item
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= options.length) {
                    if (selected.size() < max) {
                        selected.add(options[choice - 1]);
                        // For meat and cheese categories, limit to one selection and auto-finish
                        if ((category.equals("meat") || category.equals("cheese") ||
                                category.equals("extra meat") || category.equals("extra cheese")) &&
                                selected.size() >= 1) {
                            break;
                        }
                    } else {
                        System.out.println("Maximum " + max + " " + category + "s allowed.");
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException ignored) {
                System.out.println("Invalid input. Please try again.");
            }
        }
        return selected;
    }

    // Helper method for yes/no prompts with back navigation option
    private static Boolean promptYesNoOrBack(String message, Scanner scanner) {
        while (true) {
            System.out.print(message + " (y/n/b to go back): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else if (input.equals("b")) {
                return null; // Signal to go back
            } else {
                System.out.println("Please enter y, n, or b.");
            }
        }
    }
}