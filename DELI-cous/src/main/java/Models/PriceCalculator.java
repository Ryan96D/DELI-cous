package Models;

import java.math.BigDecimal;
import java.util.List;

public class PriceCalculator {

    public static BigDecimal calculateTotal(Order order) {
        BigDecimal total = BigDecimal.ZERO;

        List<? extends PricedItem> allItems = order.getAllItems(); // assume you collect all items this way

        for (PricedItem item : allItems) {
            total = total.add(item.getPrice());
        }

        return total;
    }
}