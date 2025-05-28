package models;

import models.items.PricedItem;
import util.PriceTotalCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<PricedItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(PricedItem item) {
        items.add(item);
    }

    public List<PricedItem> getAllItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return PriceTotalCalculator.calculateTotal(this);
    }
}