package menus;

import java.util.Scanner;

public class HomeScreen {
    public static void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== DELI-cious Point of Sale ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Please select an option: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    OrderScreen.start();
                    break;
                case "0":
                    System.out.println("Thank you for using DELI-cious POS. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
