package models.items;

import java.math.BigDecimal;

public interface PricedItem {
    BigDecimal getPrice();         // Total price (including extras)

    String getReceiptLine();
}