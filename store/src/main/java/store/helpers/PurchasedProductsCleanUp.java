package store.helpers;

import store.Store;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class PurchasedProductsCleanUp {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    Store store = Store.getInstance();

    public void clean() {
        final Runnable cleaner = new Runnable() {
            public void run() {
                System.out.println("all purchased products: " + (!store.getPurchasedProducts().isEmpty() ? store.getPurchasedProducts().toString().replaceAll("\\[|\\]", "").replaceAll("(Name: )", "\n \t Name: ") + "." : "[]"));
                store.clearPurchasedProducts();

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
                System.out.println(Thread.currentThread().getName()+" : cleaning purchased products on " + now.format(formatter));
                System.out.println("purchased products list after cleaning: " + store.getPurchasedProducts());
            }
        };
        //clearing purchasedProducts list every 2min.
        scheduler.scheduleAtFixedRate(cleaner, 120, 120, SECONDS);
//        scheduler.scheduleAtFixedRate(cleaner, 20, 20, SECONDS); //testing
    }
}
