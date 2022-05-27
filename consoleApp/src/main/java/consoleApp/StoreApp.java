package consoleApp;

import products.Product;
import store.Store;
import store.helpers.PurchasedProductsCleanUp;
import store.helpers.StoreHelper;


public class StoreApp {
    public static void main(String[] args) {
        Store store = Store.getInstance();
        StoreHelper helper = new StoreHelper();
        helper.fillStoreRandomly();

        new PurchasedProductsCleanUp().clean();
        Interaction.interact(helper);
    }
}
