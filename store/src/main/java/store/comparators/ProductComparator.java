package store.comparators;

import products.Product;
import store.parser.SortOrder;
import store.parser.XMLParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ProductComparator implements Comparator<Product> {
    public Map<String, String> sortTypesMap;

    @Override
    public int compare(Product p1, Product p2) {
        return p1.getName().compareTo(p2.getName());
    }

    public Comparator<Product> getComparator(String key, String value) {
        switch (key) {
            case "name":
                return checkValue(value) ? Comparator.comparing(Product::getName) : Comparator.comparing(Product::getName).reversed();
            case "price":
                return checkValue(value) ? Comparator.comparing(Product::getPrice) : Comparator.comparing(Product::getPrice).reversed();
            case "rate":
                return checkValue(value) ? Comparator.comparing(Product::getRate) : Comparator.comparing(Product::getRate).reversed();
            default:
                throw new IllegalStateException("Unexpected value: " + key);
        }
    }

    private boolean checkValue(String value) {
        return value.equals(SortOrder.ASC.toString());
    }

    public List<Product> sortProducts(List<Product> products, Map<String, String> sortTypesMap) {
        List<Product> sortedProducts = new ArrayList<>(products);
        for (Map.Entry<String, String> entry : sortTypesMap.entrySet()) {
            sortedProducts.sort(getComparator(entry.getKey(), entry.getValue()));
        }
        return sortedProducts;
    }
}


