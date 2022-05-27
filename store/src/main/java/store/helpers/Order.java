package store.helpers;

import products.Product;
import store.Store;

import java.util.List;
import java.util.Random;

public class Order implements Runnable {
    List<Product> productsOrdered;
    int threadTime = new Random().nextInt(30);

    public Order(List<Product> productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(threadTime * 1000);
//            Thread.sleep(threadTime * 100); //testing
            System.out.println(Thread.currentThread().getName() + " ordered " + productsOrdered.size() + " products: " + productsOrdered.toString().replaceAll("\\[|\\]", "").replaceAll("(Name: )", "\n \t Name: ") + ".");
            Store.getInstance().addPurchasedProducts(productsOrdered);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}