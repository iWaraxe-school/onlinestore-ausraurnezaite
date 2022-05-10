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
        categoryList.toString().replaceAll("\\[|\\]", "");
    }

    @Override
    public String toString() {
        String categories = "";
        for (Category category : categoryList) {
            categories += category.toString()+"\n";
        }
        return categories;
//        return categoryList.toString().replaceAll("\\[|\\]","");
    }
}
