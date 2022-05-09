package categories;


import products.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    private String name;
    private List<Product> productList;
    public Category(String categoryName) {
        this.name = categoryName;
        this.productList = new ArrayList<Product>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Category: name '" + name + "' , product list: " + productList;
    }
}
