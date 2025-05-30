package menus;

import enums.ChipFlavor;
import enums.Size;
import models.items.Chips;
import utils.MenuDisplayHelper;

import java.util.Scanner;

public class ChipsBuilderMenu {

    /**
     * Displays chip flavor options and processes user selection.
     *
     * @return Chips object with selected flavor, or null if invalid input
     */
    public static Chips start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Add Chips ===");

        // Use Size.SMALL as default since chips have flat pricing regardless of size
        MenuDisplayHelper.displayOptionsWithPrices("chip", ChipFlavor.values(), Size.SMALL, false);

        System.out.print("Enter choice: ");

        String input = scanner.nextLine();
        int chipChoice;

        // Parse user input and validate numeric format
        try {
            chipChoice = Integer.parseInt(input) - 1; // Convert to 0-based index
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, no chips added.");
            return null;
        }

        // Validate selection is within valid range
        if (chipChoice < 0 || chipChoice >= ChipFlavor.values().length) {
            System.out.println("Invalid choice, no chips added.");
            return null;
        }

        ChipFlavor selectedChipFlavor = ChipFlavor.values()[chipChoice];

        // Format enum name for display (replace underscores with spaces)
        System.out.println("Added " + selectedChipFlavor.toString().replace('_', ' ') + " chips.");

        return new Chips(selectedChipFlavor);
    }
}