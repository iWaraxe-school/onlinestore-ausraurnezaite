package consoleApp;

import consoleApp.commands.AllProductsByPriceCommand;
import consoleApp.commands.CreateOrderCommand;
import consoleApp.commands.Top5Command;
import store.comparators.helpers.StoreHelper;

import java.util.Scanner;

public class Interaction {
    public static void interact(StoreHelper helper) {

        Scanner sc = new Scanner(System.in);
        String action = "";
        while (!"quit".equalsIgnoreCase(action)) {
            System.out.println("Choose the action:  sort/top/order/quit: ");
            action = sc.nextLine();
            switch (action) {
                case "sort":
                    new AllProductsByPriceCommand(helper).execute();
                    break;
                case "top":
                    new Top5Command(helper).execute();
                    break;
                case "order":
                    new CreateOrderCommand(helper).execute();
                    break;
                case "quit":
                    System.out.println("bye");
                    break;
                default:
                    System.out.println("Unexpected command: " + action);
            }
        }
    }
}