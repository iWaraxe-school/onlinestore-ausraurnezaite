package store;

import categories.Category;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import products.Product;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class Store {
    private List<Category> categoriesList;
    private List<Product> purchasedProductsList;
    private static volatile Store store;

    private Store() {
        purchasedProductsList = Collections.synchronizedList(new ArrayList<>());

        List<Category> categories = new ArrayList<>();
        Reflections reflections = new Reflections("categories", new SubTypesScanner());
        //Get all existing subtypes of category class
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);
        for (Class<? extends Category> type : subTypes) {
            try {
                Category category = type.getConstructor().newInstance();
                categories.add(category);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        this.categoriesList = categories;
    }

    //  Thread-safe Singleton with lazy loading
    public static Store getInstance() {
        if (store == null) {
            synchronized (Store.class) {
                if (store == null) {
                    store = new Store();
                }
            }
        }
        return store;
    }

    public void addCategory(Category category) {
        this.categoriesList.add(category);
    }

    public List<Category> getCategoriesList() {
        return categoriesList;
    }

    public void addPurchasedProducts(List<Product> products) {
        this.purchasedProductsList.addAll(products);
    }

    public List<Product> getPurchasedProducts() {
        return purchasedProductsList;
    }

    public void clearPurchasedProducts() {
        purchasedProductsList.clear();
    }

    @Override
    public String toString() {
        String categoriesFull = "All products in store: \n";
        for (Category category : categoriesList) {
            categoriesFull += category.toString() + "\n";
        }
        return categoriesFull;
    }
}
