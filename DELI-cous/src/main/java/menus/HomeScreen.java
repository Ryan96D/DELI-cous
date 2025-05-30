package menus;

import java.util.Scanner;

public class HomeScreen {

    /**
     * Main entry point for the POS application. Displays the main menu and handles
     * navigation to order processing or application exit.
     */
    public static void start() {
        Scanner scanner = new Scanner(System.in);

        // Main application loop - continues until user selects exit
        while (true) {
            System.out.println( //ascii art
                    "\n      :::::::::  :::   :::   :::     ::::    ::: ::: ::::::::          :::::::::  :::::::::: :::        ::::::::::: \n" +
                    "     :+:    :+: :+:   :+: :+: :+:   :+:+:   :+: :+ :+:    :+:         :+:    :+: :+:        :+:            :+:      \n" +
                    "    +:+    +:+  +:+ +:+ +:+   +:+  :+:+:+  +:+    +:+                +:+    +:+ +:+        +:+            +:+       \n" +
                    "   +#++:++#:    +#++: +#++:++#++: +#+ +:+ +#+    +#++:++#++         +#+    +:+ +#++:++#   +#+            +#+        \n" +
                    "  +#+    +#+    +#+  +#+     +#+ +#+  +#+#+#           +#+         +#+    +#+ +#+        +#+            +#+         \n" +
                    " #+#    #+#    #+#  #+#     #+# #+#   #+#+#    #+#    #+#         #+#    #+# #+#        #+#            #+#          \n" +
                    "###    ###    ###  ###     ### ###    ####     ########          #########  ########## ########## ########### \n");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Please select an option: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    // Navigate to order creation workflow
                    OrderScreen.start();
                    break;
                case "0":
                    // Clean exit from application
                    System.out.println("Thank you for using DELI-cious POS. Goodbye!");
                    return;
                default:
                    // Handle invalid input and continue loop
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}