package models.items;

import enums.Size;
import enums.DrinkFlavor;
import java.math.BigDecimal;

public class Drinks implements PricedItem {
    private Size size;
    private DrinkFlavor flavor;

    public Drinks(DrinkFlavor flavor, Size size) {
        this.flavor = flavor;
        this.size = size;
    }

    public DrinkFlavor getFlavor() {
        return flavor;
    }

    public void setFlavor(DrinkFlavor flavor) {
        this.flavor = flavor;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

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

    @Override
    public String toString() {
        return size + " " + flavor;
    }
}