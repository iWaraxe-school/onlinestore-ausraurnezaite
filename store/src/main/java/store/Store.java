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

    public Store() {
        this.categoryList = new ArrayList<>();
    }

    public void addCategory(Category category) {
        this.categoryList.add(category);
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Product> getAllProducts() {
        return categoryList.stream()
                .map(category -> category.getProductList())
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
    }

    public void printCategories() {
        String categories = "";
        for (Category category : categoryList) {
            categories += category.getName() + "\n";
        }
        System.out.println(categories);
//        System.out.println(categoryList.toString().replaceAll("\\[|\\]", ""));
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

    public List<Product> getTopFiveProductList() {
        Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\priceDESC.xml");
        List<Product> sorted = new ProductComparator(sortTypesMap).sortProducts(getAllProducts()).subList(0, 5);
        return sorted;
    }

    public void top5() {
        StringBuilder stringBuilder = new StringBuilder("Top five products by price:\n");
        getTopFiveProductList().forEach(product -> stringBuilder.append(product + "\n"));
        System.out.println(stringBuilder);
    }

    public List<Product> getAllProductsByPriceListASC() {
        Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\priceASC.xml");
        List<Product> sorted = new ProductComparator(sortTypesMap).sortProducts(getAllProducts());
        return sorted;
    }

    public void allProductsByPrice() {
        StringBuilder stringBuilder = new StringBuilder("All products by price:\n");
        getAllProductsByPriceListASC().forEach(product -> stringBuilder.append(product + "\n"));
        System.out.println(stringBuilder);
    }

    public void printSortedStore() {
        Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\priceASC.xml");
        List<Category> categories = new ArrayList<>(categoryList);
        StringBuilder stringBuilder = new StringBuilder("All categories sorted by price: \n");
        categories.forEach(category -> {
            stringBuilder.append("Category: " + category.getName() + " Products:");
            List<Product> products = category.getProductList();
            List<Product> sortedProductsInCategory = new ProductComparator(sortTypesMap).sortProducts(products);
            stringBuilder.append(sortedProductsInCategory.toString().replaceAll("\\[|\\]", "").replaceAll("(Name: )", "\n \t Name: ") + ".\n");
//            sortedProductsInCategory.forEach(product -> stringBuilder.append(product + "\n"));
        });
        System.out.println(stringBuilder);
    }

}
