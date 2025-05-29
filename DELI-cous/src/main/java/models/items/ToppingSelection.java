package models.items;

public class ToppingSelection<T> {
    private T topping;
    private boolean isExtra;

    public ToppingSelection(T topping, boolean isExtra) {
        this.topping = topping;
        this.isExtra = isExtra;
    }

    public T getTopping() {
        return topping;
    }

    public boolean isExtra() {
        return isExtra;
    }

    @Override
    public String toString() {
        return topping.toString() + (isExtra ? " (extra)" : "");
    }
}