package consoleApp;

import store.Store;

import java.util.Scanner;

public class Interaction {
    public static void interact(Store store) {

        Scanner sc = new Scanner(System.in);
        String action = "";
        while (!"quit".equalsIgnoreCase(action)) {
            System.out.println("Choose the action:  sort/top/quit: ");
            action = sc.nextLine();
            switch (action) {
                case "sort":
                    store.allProductsByPrice();
                    store.printSortedStore();
                    break;
                case "top":
                    store.top5();
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
