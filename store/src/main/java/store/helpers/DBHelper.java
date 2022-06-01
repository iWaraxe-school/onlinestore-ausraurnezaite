package store.helpers;

import categories.Category;
import com.github.javafaker.Cat;
import products.Product;
import store.Store;
import store.parser.XMLParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DBHelper implements Helper {
    public static Connection connection;

    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:test;INIT=runscript from 'store/src/main/resources/init.sql'", "sa", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fillStoreRandomly() {
        try {
            List<Category> categories = Store.getInstance().getCategoriesList();
            for (int i = 0; i < categories.size(); i++) {
                PreparedStatement insertCategories = connection.prepareStatement("INSERT INTO categories(id, name) VALUES(?, ?)");
                insertCategories.setInt(1, i + 1);
                insertCategories.setString(2, categories.get(i).getName().toString());
                insertCategories.execute();
                int randomProductAmountToAdd = new Random().nextInt(10) + 1;
                for (int j = 0; j < randomProductAmountToAdd; j++) {
                    PreparedStatement insertProduct = connection.prepareStatement("INSERT INTO products(category_id, name, rate, price) VALUES(?, ?, ?, ?)");
                    insertProduct.setInt(1, i + 1);
                    insertProduct.setString(2, getRandomProductName(categories.get(i).getName()));
                    insertProduct.setDouble(3, getRandomProductRate());
                    insertProduct.setDouble(4, getRandomProductPrice());
                    insertProduct.execute();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void top5() {
        try (Statement stmt = DBHelper.connection.createStatement()) {
            Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\priceDESC.xml");
            for (Map.Entry<String, String> entry : sortTypesMap.entrySet()) {

                ResultSet productsRS = stmt.executeQuery("select * from products order by " + entry.getKey() + " " + entry.getValue() + " limit 5");
                System.out.println("Top5:");
                while (productsRS.next()) {
                    System.out.println("Name: " + productsRS.getString("name") + ", rate: " + productsRS.getDouble("rate") + " price: " + productsRS.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void allProductsByPrice() {
        try (Statement stmt = DBHelper.connection.createStatement()) {
            Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\priceASC.xml");
            for (Map.Entry<String, String> entry : sortTypesMap.entrySet()) {

                ResultSet productsRS = stmt.executeQuery("select * from products order by " + entry.getKey() + " " + entry.getValue());
                System.out.println("All products by price:");
                while (productsRS.next()) {
                    System.out.println("Name: " + productsRS.getString("name") + ", rate: " + productsRS.getDouble("rate") + " price: " + productsRS.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printSortedStore() {
        try (Statement stmt = DBHelper.connection.createStatement()) {
            Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\priceASC.xml");
            for (Map.Entry<String, String> entry : sortTypesMap.entrySet()) {
                ResultSet productsRS = stmt.executeQuery("select * from products join categories on products.category_id = categories.id order by products.category_id, " + entry.getKey() + " " + entry.getValue());
                System.out.println("All categories sorted by price:");
                while (productsRS.next()) {
                    System.out.println(productsRS.getString("categories.name") + " Name: " + productsRS.getString("name") + ", rate: " + productsRS.getDouble("rate") + " price: " + productsRS.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createOrder() {
        executorService.execute(new Order(getRandomProductsList()));
    }

    public List<Product> getRandomProductsList() {
        List<Product> randomProducts = new ArrayList<>();
        int amountOfProductsOrdering = new Random().nextInt(5) + 1;

        try (Statement stmt = DBHelper.connection.createStatement()) {
            ResultSet productsCountRS = stmt.executeQuery("select count(name) as count from products");
            int productsCount = 0;      //total amount of products in the store
            if (productsCountRS.next()) {
                productsCount = productsCountRS.getInt("count");
//                System.out.println("total products in store: "+productsCount);
            }

//            System.out.println("ordering: " + amountOfProductsOrdering);
            for (int i = 0; i < amountOfProductsOrdering; i++) {
                ResultSet productsRS = stmt.executeQuery("select * from products");
                //getting random product from the store
                int randomProductNumber = new Random().nextInt(productsCount);
//                System.out.println("ordering product number: " + randomProductNumber);
                for (int j = 0; j <= randomProductNumber; j++) {
                    productsRS.next();
                }
                String name = productsRS.getString("name");
                double rate = productsRS.getDouble("rate");
                double price = productsRS.getDouble("price");
                randomProducts.add(new Product.Builder()
                        .name(name)
                        .rate(rate)
                        .price(price)
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        randomProducts.stream().forEach(System.out::println);
        return randomProducts;
    }
}
