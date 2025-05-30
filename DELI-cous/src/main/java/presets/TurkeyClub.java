package presets;
import enums.*;
import models.items.Sandwich;
import java.util.List;

public class TurkeyClub extends Sandwich {
    public TurkeyClub() {
        super(Bread.WHITE, Size.MEDIUM);
        setMeats(List.of(Meat.TURKEY, Meat.BACON));
        setCheeses(List.of(Cheese.SWISS));
        setToppings(List.of(Topping.LETTUCE, Topping.TOMATOES));
        setSauces(List.of(Sauce.MAYO));
        setToasted(true);
    }
}