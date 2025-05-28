package Models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<PricedItem> items = new ArrayList<>();

    public void addItem(PricedItem item) {
        items.add(item);
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(PricedItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void printReceipt() {
        for (PricedItem item : items) {
            System.out.println(item.getReceiptLine());
        }
        System.out.println("Total: $" + getTotal());
    }
}