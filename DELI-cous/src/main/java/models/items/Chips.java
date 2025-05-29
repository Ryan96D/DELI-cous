package models.items;

import enums.Chip;

import java.math.BigDecimal;

public class Chips implements PricedItem {
    private Chip type;

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("1.50");
    }

    @Override
    public String getReceiptLine() {
        return type + " Chips - $" + getPrice();
    }
}
