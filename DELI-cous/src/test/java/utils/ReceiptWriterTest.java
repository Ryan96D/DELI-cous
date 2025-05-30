package utils;

import models.Order;
import models.items.Chips;
import models.items.Drinks;
import models.items.Sandwich;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;

public class ReceiptWriterTest {




    //Test that receipt saves to a .txt file

    @Test
    void testReceiptSavesToFile() {
        // Count existing files before test
        File receiptsFolder = new File("receipts");
        int filesBefore = 0;
        if (receiptsFolder.exists()) {
            File[] existingFiles = receiptsFolder.listFiles((dir, name) -> name.endsWith(".txt"));
            filesBefore = existingFiles != null ? existingFiles.length : 0;
        }

        // Create simple test order
        Order order = new Order() {
            public java.util.List<Sandwich> getSandwiches() { return Arrays.asList(); }
            public java.util.List<Drinks> getDrinks() { return Arrays.asList(); }
            public java.util.List<Chips> getChips() { return Arrays.asList(); }
            public BigDecimal getTotalPrice() { return new BigDecimal("10.00"); }
        };

        // Write receipt
        ReceiptWriter.writeReceipt(order);

        // Verify file was created
        assertTrue(receiptsFolder.exists(), "Receipts folder should exist");

        File[] txtFiles = receiptsFolder.listFiles((dir, name) -> name.endsWith(".txt"));
        assertNotNull(txtFiles, "Should have txt files array");
        assertEquals(filesBefore + 1, txtFiles.length, "Should create one new .txt file");
    }
}