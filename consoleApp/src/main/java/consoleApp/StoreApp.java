package consoleApp;

import store.Store;
import store.comparators.helpers.StoreHelper;


public class StoreApp {
    public static void main(String[] args) {
        Store store = Store.getInstance();
        StoreHelper helper = new StoreHelper(store);
        helper.fillStoreRandomly();
        Interaction.interact(helper);
    }
}
