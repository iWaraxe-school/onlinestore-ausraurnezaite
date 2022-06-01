package consoleApp;

import consoleApp.commands.*;
import store.helpers.Helper;

import java.util.Scanner;

public class Interaction {
    public static void interact(Helper helper) {

        Scanner sc = new Scanner(System.in);
        String action = "";
        while (!"quit".equalsIgnoreCase(action)) {
            System.out.println("Choose the action:  sort/list/top/order/quit: ");
            action = sc.nextLine().toLowerCase();
            switch (action) {
                case "sort":
                    new CategoriesSortedByPriceCommand(helper).execute();
                    break;
                case "list":
                    new AllProductsByPriceCommand(helper).execute();
                    break;
                case "top":
                    new Top5Command(helper).execute();
                    break;
                case "order":
                    new CreateOrderCommand(helper).execute();
                    break;
                case "quit":
                    new QuitCommand(helper).execute();
                    break;
                default:
                    System.out.println("Unexpected command: " + action);
            }
        }
    }
}