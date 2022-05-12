package store.helpers;

import categories.Category;
import products.Product;
import store.Store;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class StoreHelper {
    Store store;

    public StoreHelper(Store store) {
        this.store = store;
    }

    public void fillStoreRandomly() {
        RandomStorePopulator populator = new RandomStorePopulator();
        Map<Category, Integer> categoryProductsMapToAdd = createProductListToAdd();

        for (Map.Entry<Category, Integer> entry : categoryProductsMapToAdd.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {

                Product product = new Product(
                        populator.randomProductName(entry.getKey().getName()),
                        populator.randomProductRate(),
                        populator.randomProductPrice());
                entry.getKey().addProduct(product);
            }
            this.store.addCategory(entry.getKey());
        }
    }


    public static Map<Category, Integer> createProductListToAdd() {
        Map<Category, Integer> productsToAdd = new HashMap<>();

        Reflections reflections = new Reflections("categories", new SubTypesScanner());

        //Get all existing subtypes of category
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        //Create random number of random products for each category
        for (Class<? extends Category> type : subTypes) {
            try {
                Random random = new Random();
                productsToAdd.put(type.getConstructor().newInstance(), random.nextInt(10));

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return productsToAdd;
    }
}