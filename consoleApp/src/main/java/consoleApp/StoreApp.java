package consoleApp;

import store.helpers.DBHelper;

import store.helpers.HTTP.Server;
import store.helpers.PurchasedProductsCleanUp;


public class StoreApp {
    public static void main(String[] args) {

        //store app can run in two modes - with DB/without DB. It depends on the helper used further  StoreHelper/DBHelper
//        StoreHelper storeHelper = new StoreHelper();
        DBHelper dbHelper = new DBHelper();


//        storeHelper.fillStoreRandomly();
        dbHelper.fillStoreRandomly();

        Server.startServer();


        new PurchasedProductsCleanUp().clean();


//        Interaction.interact(storeHelper);
//        Interaction.interact(dbHelper);
    }
}
