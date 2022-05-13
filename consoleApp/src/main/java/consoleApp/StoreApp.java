package consoleApp;

import store.Store;
import store.helpers.StoreHelper;
import store.parser.XMLParser;


import java.util.Map;

import static store.parser.XMLParser.getSortTypes;

public class StoreApp {
    public static void main(String[] args) {
        Store store = new Store();
        StoreHelper helper = new StoreHelper(store);
        helper.fillStoreRandomly();
        store.printCategories();
        System.out.println(store);

        Map<String, String> sortTypesMap = XMLParser.getSortTypes();
        System.out.printf(sortTypesMap.toString());
    }
}
