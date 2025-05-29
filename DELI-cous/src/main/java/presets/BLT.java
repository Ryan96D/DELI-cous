package presets;
import enums.*;
import models.items.Sandwich;
import java.util.List;

public class BLT extends Sandwich {
    public BLT() {
        super(Bread.WHITE, Size.MEDIUM);
        setMeats(List.of(Meat.BACON));
        setCheeses(List.of(Cheese.CHEDDAR));
        setToppings(List.of(Topping.LETTUCE, Topping.TOMATOES));
        setSauces(List.of(Sauce.RANCH));
        setToasted(true);
    }
}