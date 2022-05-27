package store.comparators.helpers;

import categories.Category;
import products.Product;
import store.Store;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import store.comparators.ProductComparator;
import store.parser.XMLParser;

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

                Product product = new Product.Builder()
                        .name(populator.randomProductName(entry.getKey().getName()))
                        .rate(populator.randomProductRate())
                        .price(populator.randomProductPrice())
                        .build();
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

    public List<Product> getAllProducts() {
        return store.getCategoryList().stream()
                .map(category -> category.getProductList())
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
    }

    public void printCategories() {
        String categories = "";
        for (Category category : store.getCategoryList()) {
            categories += category.getName() + "\n";
        }
        System.out.println(categories);
    }


    public List<Product> getTop5ProductList() {
        Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\priceDESC.xml");
        List<Product> sorted = new ProductComparator().sortProducts(getAllProducts(), sortTypesMap).subList(0, 5);
        return sorted;
    }

    public void top5() {
        StringBuilder stringBuilder = new StringBuilder("Top five products by price:\n");
        getTop5ProductList().forEach(product -> stringBuilder.append(product + "\n"));
        System.out.println(stringBuilder);
    }

    public List<Product> getAllProductsByPriceListASC() {
        Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\priceASC.xml");
        List<Product> sorted = new ProductComparator().sortProducts(getAllProducts(), sortTypesMap);
        return sorted;
    }

    public void allProductsByPrice() {
        StringBuilder stringBuilder = new StringBuilder("All products by price:\n");
        getAllProductsByPriceListASC().forEach(product -> stringBuilder.append(product + "\n"));
        System.out.println(stringBuilder);
    }

    public void printSortedStore() {
        Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\priceASC.xml");
        List<Category> categories = new ArrayList<>(store.getCategoryList());
        StringBuilder stringBuilder = new StringBuilder("All categories sorted by price: \n");
        categories.forEach(category -> {
            stringBuilder.append("Category: " + category.getName() + " Products:");
            List<Product> products = category.getProductList();
            List<Product> sortedProductsInCategory = new ProductComparator().sortProducts(products, sortTypesMap);
            stringBuilder.append(sortedProductsInCategory.toString().replaceAll("\\[|\\]", "").replaceAll("(Name: )", "\n \t Name: ") + ".\n");
        });
        System.out.println(stringBuilder);
    }
}