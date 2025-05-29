package menus;

import enums.ChipFlavor;
import enums.Size;
import models.items.Chips;
import utils.MenuDisplayHelper;

import java.util.Scanner;

public class ChipsBuilderMenu {

    public static Chips start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Add Chips ===");

        // Pass Size.SMALL as default value since chips have flat pricing
        MenuDisplayHelper.displayOptionsWithPrices("chip", ChipFlavor.values(), Size.SMALL, false);

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