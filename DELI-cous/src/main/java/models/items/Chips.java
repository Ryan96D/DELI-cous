package models.items;

import enums.ChipFlavor;

import java.math.BigDecimal;

public class Chips implements PricedItem {
    private ChipFlavor type;

    public Chips(ChipFlavor type) {
        this.type = type;
    }

    public ChipFlavor getType() {
        return type;
    }

    public void setType(ChipFlavor type) {
        this.type = type;
    }

    @Override
    public BigDecimal getPrice() {
        // fixed price for any chips bag
        return new BigDecimal("1.50");
    }

    @Override
    public String getReceiptLine() {
        return type + " Chips - $" + getPrice();
    }
}