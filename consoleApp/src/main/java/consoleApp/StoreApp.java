package consoleApp;

import products.Product;
import store.Store;
import store.helpers.StoreHelper;
import store.parser.XMLParser;
import store.comparators.ProductComparator;


import java.util.List;
import java.util.Map;


public class StoreApp {
    public static void main(String[] args) {
        Store store = new Store();
        StoreHelper helper = new StoreHelper(store);
        helper.fillStoreRandomly();
        store.printCategories();
        System.out.println(store);

        Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\config.xml");
        System.out.println("Sorting types: " + sortTypesMap.toString()+ "\n");

        store.top5();
        store.allProductsByPrice();
        store.printSortedStore();
    }
}
