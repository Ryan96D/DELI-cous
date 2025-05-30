package presets;

import enums.*;
import models.items.Sandwich;

import java.util.ArrayList;
import java.util.List;

public class WonderBreadDeluxe extends Sandwich {
    public WonderBreadDeluxe() {
        super(Bread.WHITE, Size.SMALL);
        setMeats(new ArrayList<>());
        setCheeses(new ArrayList<>());
        setToppings(new ArrayList<>());
        setSauces(new ArrayList<>());
        setToasted(false);
    }
}

