package menus;

import enums.Chip;
import models.items.Chips;

import java.util.Scanner;

public class ChipsBuilderMenu {

    public static Chips start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Add Chips ===");

        // Show chip options
        System.out.println("Select your chips flavor:");
        for (Chip chip : Chip.values()) {
            System.out.println((chip.ordinal() + 1) + ") " + chip.toString().replace('_', ' '));
        }
        System.out.print("Enter choice: ");

        String input = scanner.nextLine();
        int chipChoice;
        try {
            chipChoice = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, no chips added.");
            return null;
        }

        if (chipChoice < 0 || chipChoice >= Chip.values().length) {
            System.out.println("Invalid choice, no chips added.");
            return null;
        }

        Chip selectedChip = Chip.values()[chipChoice];
        System.out.println("Added " + selectedChip.toString().replace('_', ' ') + " chips.");

        return new Chips(selectedChip);
    }
}