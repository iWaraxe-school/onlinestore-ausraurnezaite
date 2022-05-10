package consoleApp;

import store.Store;
import store.helpers.StoreHelper;

public class StoreApp {
    public static void main(String[] args) {
        Store store = new Store();
        StoreHelper helper = new StoreHelper(store);
        helper.fillStoreRandomly();
        store.printCategories();
    }
}
