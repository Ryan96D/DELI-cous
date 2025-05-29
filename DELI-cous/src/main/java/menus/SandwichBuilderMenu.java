
package menus;

import enums.*;
import models.items.Sandwich;
import utils.MenuDisplayHelper;

import java.util.*;

public class SandwichBuilderMenu {

    private enum Step {
        BREAD, SIZE, MEAT, EXTRA_MEAT_PROMPT, EXTRA_MEAT, CHEESE, EXTRA_CHEESE_PROMPT, EXTRA_CHEESE, TOPPINGS, SAUCES, TOASTED, COMPLETE
    }

    public static Sandwich start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Build Your Sandwich ===");

        // Initialize sandwich components
        Bread selectedBread = null;
        Size selectedSize = null;
        List<Meat> selectedMeats = new ArrayList<>();
        List<Cheese> selectedCheeses = new ArrayList<>();
        List<Topping> selectedToppings = new ArrayList<>();
        List<Sauce> selectedSauces = new ArrayList<>();
        boolean toasted = false;
        boolean wantsExtraMeat = false;
        boolean wantsExtraCheese = false;

        Step currentStep = Step.BREAD;

        while (currentStep != Step.COMPLETE) {
            switch (currentStep) {
                case BREAD:
                    selectedBread = chooseOption("bread", Bread.values(), scanner);
                    if (selectedBread != null) {
                        currentStep = Step.SIZE;
                    }
                    break;

                case SIZE:
                    selectedSize = chooseOption("size (4\", 8\", 12\")", Size.values(), scanner);
                    if (selectedSize != null) {
                        currentStep = Step.MEAT;
                    } else {
                        currentStep = Step.BREAD;
                    }
                    break;

                case MEAT:
                    List<Meat> meats = chooseMultiple("meat", Meat.values(), scanner, 1, selectedSize, false);
                    if (meats == null) { // User pressed B
                        currentStep = Step.SIZE;
                    } else if (meats.isEmpty()) {
                        System.out.println("You must select at least one meat.");
                        // Stay on same step
                    } else {
                        selectedMeats = new ArrayList<>(meats);
                        currentStep = Step.EXTRA_MEAT_PROMPT;
                    }
                    break;

                case EXTRA_MEAT_PROMPT:
                    Boolean extraMeatChoice = promptYesNoOrBack("Would you like extra meat?", scanner);
                    if (extraMeatChoice == null) { // User pressed B
                        currentStep = Step.MEAT;
                    } else {
                        wantsExtraMeat = extraMeatChoice;
                        currentStep = wantsExtraMeat ? Step.EXTRA_MEAT : Step.CHEESE;
                    }
                    break;

                case EXTRA_MEAT:
                    List<Meat> extraMeats = chooseMultiple("extra meat", Meat.values(), scanner, 1, selectedSize, true);
                    if (extraMeats == null) { // User pressed B
                        currentStep = Step.EXTRA_MEAT_PROMPT;
                    } else {
                        selectedMeats.addAll(extraMeats);
                        currentStep = Step.CHEESE;
                    }
                    break;

                case CHEESE:
                    List<Cheese> cheeses = chooseMultiple("cheese", Cheese.values(), scanner, 1, selectedSize, false);
                    if (cheeses == null) { // User pressed B
                        currentStep = wantsExtraMeat ? Step.EXTRA_MEAT : Step.EXTRA_MEAT_PROMPT;
                    } else if (cheeses.isEmpty()) {
                        System.out.println("You must select at least one cheese.");
                        // Stay on same step
                    } else {
                        selectedCheeses = new ArrayList<>(cheeses);
                        currentStep = Step.EXTRA_CHEESE_PROMPT;
                    }
                    break;

                case EXTRA_CHEESE_PROMPT:
                    Boolean extraCheeseChoice = promptYesNoOrBack("Would you like extra cheese?", scanner);
                    if (extraCheeseChoice == null) { // User pressed B
                        currentStep = Step.CHEESE;
                    } else {
                        wantsExtraCheese = extraCheeseChoice;
                        currentStep = wantsExtraCheese ? Step.EXTRA_CHEESE : Step.TOPPINGS;
                    }
                    break;

                case EXTRA_CHEESE:
                    List<Cheese> extraCheeses = chooseMultiple("extra cheese", Cheese.values(), scanner, 1, selectedSize, true);
                    if (extraCheeses == null) { // User pressed B
                        currentStep = Step.EXTRA_CHEESE_PROMPT;
                    } else {
                        selectedCheeses.addAll(extraCheeses);
                        currentStep = Step.TOPPINGS;
                    }
                    break;

                case TOPPINGS:
                    List<Topping> toppings = chooseMultiple("topping", Topping.values(), scanner, 10, selectedSize, false);
                    if (toppings == null) { // User pressed B
                        currentStep = wantsExtraCheese ? Step.EXTRA_CHEESE : Step.EXTRA_CHEESE_PROMPT;
                    } else {
                        selectedToppings = toppings;
                        currentStep = Step.SAUCES;
                    }
                    break;

                case SAUCES:
                    List<Sauce> sauces = chooseMultiple("sauce", Sauce.values(), scanner, 5, selectedSize, false);
                    if (sauces == null) { // User pressed B
                        currentStep = Step.TOPPINGS;
                    } else {
                        selectedSauces = sauces;
                        currentStep = Step.TOASTED;
                    }
                    break;

                case TOASTED:
                    Boolean toastedChoice = promptYesNoOrBack("Would you like the sandwich toasted?", scanner);
                    if (toastedChoice == null) { // User pressed B
                        currentStep = Step.SAUCES;
                    } else {
                        toasted = toastedChoice;
                        currentStep = Step.COMPLETE;
                    }
                    break;
            }
        }

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
            System.out.print("Enter choice (B to go back): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("B")) {
                return null; // Signal to go back
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= options.length) {
                    return options[choice - 1];
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid input. Please try again.");
        }
    }

    private static <T extends Enum<T>> List<T> chooseMultiple(String category, T[] options, Scanner scanner, int max, Size size, boolean isExtra) {
        List<T> selected = new ArrayList<>();
        boolean requireAtLeastOne = (category.equals("meat") || category.equals("cheese")) && !isExtra;

        while (true) {
            System.out.println("\nCurrent " + category + "s: " + selected);
            System.out.println("Select " + category + " (0 to finish, B to go back, R to remove):");
            MenuDisplayHelper.displayOptionsWithPrices(category, options, size, isExtra);
            System.out.print("Choice: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("0")) {
                if (requireAtLeastOne && selected.isEmpty()) {
                    System.out.println("You must select at least one " + category + ".");
                    continue;
                }
                break;
            }

            if (input.equalsIgnoreCase("B")) {
                return null; // Signal to go back
            }

            if (input.equalsIgnoreCase("R")) {
                if (selected.isEmpty()) {
                    System.out.println("Nothing to remove.");
                } else {
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

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= options.length) {
                    if (selected.size() < max) {
                        selected.add(options[choice - 1]);
                        // For meat and cheese (both regular and extra), stop after selecting one
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