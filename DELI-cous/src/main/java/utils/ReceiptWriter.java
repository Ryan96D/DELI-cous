package utils;

import models.Order;
import models.items.Chips;
import models.items.Drinks;
import models.items.Sandwich;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReceiptWriter {

    /*
     * Writes order details to a timestamped receipt txt file in the receipts directory.
     * Creates the receipts folder if it doesn't exist.

     */
    public static void writeReceipt(Order order) {
        // Ensure receipts directory exists for file storage
        File receiptsFolder = new File("receipts");
        if (!receiptsFolder.exists()) {
            receiptsFolder.mkdir();
        }

        // Generate unique filename using current timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        File receiptFile = new File(receiptsFolder, timestamp + ".txt");

        // Use try-with-resources for automatic file resource management
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile))) {
            writer.write("=== Order Receipt ===\n");

            // Write detailed breakdown for each sandwich with numbering
            int sandwichNumber = 1;
            for (Sandwich sandwich : order.getSandwiches()) {
                writer.write("\nSandwich #" + sandwichNumber++ + "\n");
                writer.write(sandwich.getReceiptLine() + "\n");
                writer.write("- Bread: " + sandwich.getBread() + "\n");
                writer.write("- Size: " + sandwich.getSize() + "\n");
                writer.write("- Meats: " + sandwich.getMeats() + "\n");
                writer.write("- Cheeses: " + sandwich.getCheeses() + "\n");
                writer.write("- Toppings: " + sandwich.getToppings() + "\n");
                writer.write("- Sauces: " + sandwich.getSauces() + "\n");
                writer.write("- Toasted: " + (sandwich.isToasted() ? "Yes" : "No") + "\n");
            }

            // Only include drinks section if drinks were ordered
            if (!order.getDrinks().isEmpty()) {
                writer.write("\nDrinks:\n");
                for (Drinks drink : order.getDrinks()) {
                    writer.write("- " + drink.getReceiptLine() + "\n");
                }
            }

            // Only include chips section if chips were ordered
            if (!order.getChips().isEmpty()) {
                writer.write("\nChips:\n");
                for (Chips chip : order.getChips()) {
                    writer.write("- " + chip.getReceiptLine() + "\n");
                }
            }

            // Format and write total price with US currency formatting
            String formattedTotal = NumberFormat.getCurrencyInstance(Locale.US)
                    .format(order.getTotalPrice());
            writer.write("\nTotal: " + formattedTotal + "\n");
            writer.write("======================\n");

            System.out.println("Receipt saved as: receipts/" + receiptFile.getName());
        } catch (IOException e) {
            // Handle file writing errors gracefully
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}