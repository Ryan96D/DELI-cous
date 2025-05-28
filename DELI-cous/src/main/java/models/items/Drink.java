package models.items;

import enums.DrinkFlavor;
import enums.Size;

import java.math.BigDecimal;

public class Drink implements PricedItem {
    private Size size;
    private DrinkFlavor flavor;

    @Override
    public BigDecimal getPrice() {
        return switch (size) {
            case SMALL -> new BigDecimal("2.00");
            case MEDIUM -> new BigDecimal("2.50");
            case LARGE -> new BigDecimal("3.00");
        };
    }

    @Override
    public String getReceiptLine() {
        return size + " " + flavor + " Drink - $" + getPrice();
    }
}