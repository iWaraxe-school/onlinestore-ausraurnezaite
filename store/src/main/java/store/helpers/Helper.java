package store.helpers;

import categories.CategoryNames;
import com.github.javafaker.Faker;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface Helper {
    void fillStoreRandomly();

    void top5();

    void allProductsByPrice();

    void printSortedStore();

    void createOrder();

    Faker faker = new Faker();

    default String getRandomProductName(CategoryNames category) {
        switch (category) {
            case BIKE:
                return faker.aviation().aircraft();
            case PHONE:
                return faker.space().planet();
            case MILK:
                return faker.food().fruit();
            default:
                return faker.color().name();
        }
    }

    default Double getRandomProductPrice() {
        return faker.number().randomDouble(2, 1, 100);
    }

    default Double getRandomProductRate() {
        return faker.number().randomDouble(2, 1, 10);
    }

    ExecutorService executorService = Executors.newFixedThreadPool(3);

    default void shutDownExecutorService() {
        executorService.shutdown();
    }
}

