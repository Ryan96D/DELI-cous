package menus;

import enums.ChipFlavor;
import models.items.Chips;

import java.util.Scanner;

public class ChipsBuilderMenu {

    public static Chips start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Add Chips ===");

        // Show chip options
        System.out.println("Select your chips flavor:");
        for (ChipFlavor chipFlavor : ChipFlavor.values()) {
            System.out.println((chipFlavor.ordinal() + 1) + ") " + chipFlavor.toString().replace('_', ' '));
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

        if (chipChoice < 0 || chipChoice >= ChipFlavor.values().length) {
            System.out.println("Invalid choice, no chips added.");
            return null;
        }

        ChipFlavor selectedChipFlavor = ChipFlavor.values()[chipChoice];
        System.out.println("Added " + selectedChipFlavor.toString().replace('_', ' ') + " chips.");

        return new Chips(selectedChipFlavor);
    }
}