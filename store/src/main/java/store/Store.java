package store;

import categories.Category;
import products.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Store {
    private List<Category> categoryList;
    private List<Product> purchasedProductsList;
    private static volatile Store store;

    private Store() {
        this.categoryList = new ArrayList<>();
        purchasedProductsList = Collections.synchronizedList(new ArrayList<>());
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
        this.categoryList.add(category);
    }

    public List<Category> getCategoryList() {
        return categoryList;
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
        for (Category category : categoryList) {
            categoriesFull += category.toString() + "\n";
        }
        return categoriesFull;
    }
}
