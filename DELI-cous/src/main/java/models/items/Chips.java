package models.items;

import enums.Chip;

import java.math.BigDecimal;

public class Chips implements PricedItem {
    private Chip type;

    public Chips(Chip type) {
        this.type = type;
    }

    public Chip getType() {
        return type;
    }

    public void setType(Chip type) {
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