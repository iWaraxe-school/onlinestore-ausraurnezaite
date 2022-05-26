package store;

import categories.Category;
import products.Product;
import store.comparators.ProductComparator;
import store.parser.XMLParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Store {
    private List<Category> categoryList;
    private static volatile Store store;

    private Store() {
        this.categoryList = new ArrayList<>();
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

    @Override
    public String toString() {
        String categoriesFull = "All products in store: \n";
        for (Category category : categoryList) {
            categoriesFull += category.toString() + "\n";
        }
        return categoriesFull;
//        return categoryList.toString().replaceAll("\\[|\\]","");
    }
}
