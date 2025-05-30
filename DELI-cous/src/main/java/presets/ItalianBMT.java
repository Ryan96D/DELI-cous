package presets;
import enums.*;
import models.items.Sandwich;
import java.util.List;

public class ItalianBMT extends Sandwich {
    public ItalianBMT() {
        super(Bread.ITALIAN, Size.MEDIUM);
        setMeats(List.of(Meat.SALAMI, Meat.HAM));
        setCheeses(List.of(Cheese.PROVOLONE));
        setToppings(List.of(Topping.LETTUCE, Topping.TOMATOES, Topping.ONIONS));
        setSauces(List.of(Sauce.VINAIGRETTE));
        setToasted(false);
    }
}