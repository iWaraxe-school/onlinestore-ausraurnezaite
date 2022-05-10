package categories;

import products.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    private CategoryNames name;
    private List<Product> productList;

    public Category(CategoryNames categoryName) {
        this.name = categoryName;
        this.productList = new ArrayList<Product>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public CategoryNames getName() {
        return name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setName(CategoryNames name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category: name '" + name + "' , product list: " + productList.toString().replaceAll("\\[|\\]", "") + ".";
    }
}
