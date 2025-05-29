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

    public static void writeReceipt(Order order) {
        // Create receipts folder if it doesn't exist
        File receiptsFolder = new File("receipts");
        if (!receiptsFolder.exists()) {
            receiptsFolder.mkdir();
        }

        // Create filename with current timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        File receiptFile = new File(receiptsFolder, timestamp + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile))) {
            writer.write("=== Order Receipt ===\n");

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

            if (!order.getDrinks().isEmpty()) {
                writer.write("\nDrinks:\n");
                for (Drinks drink : order.getDrinks()) {
                    writer.write("- " + drink.getReceiptLine() + "\n");
                }
            }

            if (!order.getChips().isEmpty()) {
                writer.write("\nChips:\n");
                for (Chips chip : order.getChips()) {
                    writer.write("- " + chip.getReceiptLine() + "\n");
                }
            }

            String formattedTotal = NumberFormat.getCurrencyInstance(Locale.US)
                    .format(order.getTotalPrice());
            writer.write("\nTotal: " + formattedTotal + "\n");
            writer.write("======================\n");

            System.out.println("Receipt saved as: receipts/" + receiptFile.getName());
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}