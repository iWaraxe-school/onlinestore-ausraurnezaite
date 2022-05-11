package store;

import categories.Category;

import java.util.ArrayList;
import java.util.List;

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
        String categoriesFull = "";
        for (Category category : categoryList) {
            categoriesFull += category.toString() + "\n";
        }
        return categoriesFull;
//        return categoryList.toString().replaceAll("\\[|\\]","");
    }
}
