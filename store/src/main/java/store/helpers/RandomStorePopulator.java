package store.helpers;

import categories.CategoryNames;
import com.github.javafaker.Faker;

public class RandomStorePopulator {
    Faker faker = new Faker();

    public RandomStorePopulator() {
    }

    public String randomProductName(CategoryNames category) {
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

    public Double randomProductPrice() {
        return faker.number().randomDouble(2, 1, 100);
    }

    public Double randomProductRate() {
        return faker.number().randomDouble(2, 1, 10);
    }
}
