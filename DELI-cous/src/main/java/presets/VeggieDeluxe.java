package presets;
import enums.*;
import models.items.Sandwich;
import java.util.List;
import java.util.ArrayList;

public class VeggieDeluxe extends Sandwich {
    public VeggieDeluxe() {
        super(Bread.WHEAT, Size.MEDIUM);
        setMeats(new ArrayList<>()); // No meat
        setCheeses(List.of(Cheese.PROVOLONE));
        setToppings(List.of(Topping.LETTUCE, Topping.TOMATOES, Topping.CUCUMBERS,
                Topping.PEPPERS, Topping.ONIONS));
        setSauces(List.of(Sauce.VINAIGRETTE));
        setToasted(false);
    }
}