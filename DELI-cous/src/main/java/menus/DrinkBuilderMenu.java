package menus;

import enums.Drink;
import enums.Size;
import models.items.DrinkItem;

import java.util.Scanner;

public class DrinkBuilderMenu {

    public static DrinkItem start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Add Drink ===");

        // Select Drink
        System.out.println("Select your drink:");
        for (Drink drink : Drink.values()) {
            System.out.println(drink.ordinal() + 1 + ") " + drink);
        }
        System.out.print("Enter choice: ");
        int drinkChoice = Integer.parseInt(scanner.nextLine()) - 1;
        Drink selectedDrink = Drink.values()[drinkChoice];

        // Select Size
        System.out.println("Select drink size:");
        for (Size size : Size.values()) {
            System.out.println(size.ordinal() + 1 + ") " + size);
        }
        System.out.print("Enter choice: ");
        int sizeChoice = Integer.parseInt(scanner.nextLine()) - 1;
        Size selectedSize = Size.values()[sizeChoice];

        DrinkItem drinkItem = new DrinkItem(selectedDrink, selectedSize);

        System.out.println("Drink added: " + drinkItem);

        return drinkItem;
    }
}