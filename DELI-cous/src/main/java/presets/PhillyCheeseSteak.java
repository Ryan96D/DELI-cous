package presets;
import enums.*;
import models.items.Sandwich;
import java.util.List;

public class PhillyCheeseSteak extends Sandwich {
    public PhillyCheeseSteak() {
        super(Bread.WHITE, Size.MEDIUM);
        setMeats(List.of(Meat.ROAST_BEEF)); // Closest to steak
        setCheeses(List.of(Cheese.AMERICAN));
        setToppings(List.of(Topping.PEPPERS));
        setSauces(List.of(Sauce.MAYO));
        setToasted(true);
    }
}