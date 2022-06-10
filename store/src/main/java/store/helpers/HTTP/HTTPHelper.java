package store.helpers.HTTP;

import categories.Category;
import products.Product;
import store.Store;
import store.helpers.DBHelper;
import store.helpers.Helper;
import store.helpers.Order;
import store.parser.XMLParser;

import java.io.*;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HTTPHelper implements Helper {
    private static Socket socket;
    private static BufferedWriter bw;
    public static boolean userLoggedIn = false;
    public static int userID;
    public static String userName;


    public HTTPHelper(Socket socket) throws IOException {
        this.socket = socket;
        OutputStream os = socket.getOutputStream();
        Writer w = new OutputStreamWriter(os, "UTF-8");
        this.bw = new BufferedWriter(w);
    }


    public void quit() throws IOException {
        bw.write("HTTP/1.1 200 OK\r\n");
        bw.write("\r\n");
        bw.write("<html>\r\n");
        bw.write("<body>\r\n");
        bw.write("<h4>Quit</h4>\r\n");
        bw.write("</body>\r\n");
        bw.write("</html>\r\n");
        bw.flush();
        try {
            DBHelper.connection.close();
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showIndex() throws IOException {

        bw.write("HTTP/1.1 200 OK\r\n");
        bw.write("\r\n");

        bw.write("<html>\r\n");
        bw.write("<body>\r\n");
        bw.write("<h1> ONLINE STORE </h1>\r\n");

        if (userLoggedIn) {
            bw.write("<h2>user: " + this.userName + " </h2>\r\n");
            bw.write("<button><a href = \"cart\" > Cart </a ></button><br>\r\n");
        } else {
            bw.write("<button><a href=\"login\"> Log in </a ></button><br>\r\n");
            bw.write("<button><a href=\"register\"> Register </a ></button><br>\r\n");
        }
        bw.write("<button><a href = \"list\" > List all products </a ></button><br>\r\n");
        bw.write("<button><a href = \"sort\" > List all categories sorted by price</a></button><br>\r\n");
        bw.write("<button><a href = \"top\" > Top 5 </a ></button><br>\r\n");

        if (userLoggedIn) {
            bw.write("<button><a href = \"logout\" > Log out </a ></button><br>\r\n");
        } else {
            bw.write("<button><a href = \"quit\" > Quit </a ></button><br>\r\n");
        }
        bw.write("</body>\r\n");
        bw.write("</html>\r\n");
        bw.write("\r\n");
        bw.flush();
    }

    @Override
    public void allProductsByPrice() {
        try {
            bw.write("HTTP/1.1 200 OK\r\n");
            bw.write("\r\n");
            bw.write("<html>\r\n");
            bw.write("<body>\r\n");

            try (Statement stmt = DBHelper.connection.createStatement()) {
                Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\priceASC.xml");
                for (Map.Entry<String, String> entry : sortTypesMap.entrySet()) {
                    ResultSet productsRS = stmt.executeQuery("select * from products order by " + entry.getKey() + " " + entry.getValue());
                    System.out.println("All products by price:");
                    bw.write("<h4>All products sorted by price:</h4>\r\n");
                    bw.write("<table>\r\n");
                    bw.write("<thead>\r\n");
                    bw.write("<th>\r\n");
                    bw.write("<td>Name</td>\r\n");
                    bw.write("<td>Rate</td>\r\n");
                    bw.write("<td>Price</td>\r\n");
                    bw.write("</th>\r\n");
                    bw.write("</thead>\r\n");
                    bw.write("<tbody>\r\n");

                    while (productsRS.next()) {
                        System.out.println("Name: " + productsRS.getString("name") + ", rate: " + productsRS.getDouble("rate") + " price: " + productsRS.getDouble("price"));
                        bw.write("<tr>\r\n");
                        bw.write("<td>\r\n");
                        bw.write("<td>" + productsRS.getString("name") + "<td>" + productsRS.getDouble("rate") + "<td>" + productsRS.getDouble("price") + "\r\n");
                        if (userLoggedIn) {
                            bw.write("<td><button><a href=\"order_" + productsRS.getInt("id") + "\">add to cart</a></button><br></td>\r\n");
                        }
                        bw.write("</tr>\r\n");
                    }
                    bw.write("<tbody>\r\n");
                    bw.write("<table>\r\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            bw.write("<button> <a href=\"/\">BACK</a></button>\r\n");
            bw.write("</body>\r\n");
            bw.write("</html>\r\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printSortedStore() {
        try {
            bw.write("HTTP/1.1 200 OK\r\n");
            bw.write("\r\n");
            bw.write("<html>\r\n");
            bw.write("<body>\r\n");

            try (Statement stmt = DBHelper.connection.createStatement()) {
                Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\priceASC.xml");
                for (Map.Entry<String, String> entry : sortTypesMap.entrySet()) {
                    ResultSet productsRS = stmt.executeQuery("select * from products join categories on products.category_id = categories.id order by products.category_id, " + entry.getKey() + " " + entry.getValue());
                    System.out.println("All categories sorted by price:");
                    bw.write("<h4>All categories sorted by price:</h4>\r\n");
                    bw.write("<table>\r\n");
                    bw.write("<thead>\r\n");
                    bw.write("<th>\r\n");
                    bw.write("<td>Category</td>\r\n");
                    bw.write("<td>Name</td>\r\n");
                    bw.write("<td>Rate</td>\r\n");
                    bw.write("<td>Price</td>\r\n");
                    bw.write("</th>\r\n");
                    bw.write("</thead>\r\n");
                    bw.write("<tbody>\r\n");

                    while (productsRS.next()) {
                        System.out.println(productsRS.getString("categories.name") + " Name: " + productsRS.getString("name") + ", rate: " + productsRS.getDouble("rate") + " price: " + productsRS.getDouble("price"));
                        bw.write("<tr>\r\n");
                        bw.write("<td>\r\n");
                        bw.write("<td>" + productsRS.getString("categories.name") + "<td>" + productsRS.getString("name") + "<td>" + productsRS.getDouble("rate") + "<td>" + productsRS.getDouble("price") + "\r\n");
                        if (userLoggedIn) {
                            bw.write("<td> <button><a href=\"order_" + productsRS.getInt("id") + "\">add to cart</a></button><br></td>\r\n");
                        }
                        bw.write("</tr>\r\n");
                    }
                    bw.write("<tbody>\r\n");
                    bw.write("<table>\r\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            bw.write("<button> <a href=\"/\">BACK</a></button>\r\n");
            bw.write("</body>\r\n");
            bw.write("</html>\r\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void top5() {
        try {
            bw.write("HTTP/1.1 200 OK\r\n");
            bw.write("\r\n");
            bw.write("<html>\r\n");
            bw.write("<body>\r\n");

            try (Statement stmt = DBHelper.connection.createStatement()) {
                Map<String, String> sortTypesMap = XMLParser.getSortTypes("C:\\Users\\AusraUrnezaite\\IdeaProjects\\onlinestore-ausraurnezaite\\store\\src\\main\\resources\\priceDESC.xml");
                for (Map.Entry<String, String> entry : sortTypesMap.entrySet()) {

                    ResultSet productsRS = stmt.executeQuery("select * from products order by " + entry.getKey() + " " + entry.getValue() + " limit 5");
                    System.out.println("Top5:");
                    bw.write("<h4>All categories sorted by price:</h4>\r\n");
                    bw.write("<table>\r\n");
                    bw.write("<thead>\r\n");
                    bw.write("<th>\r\n");
                    bw.write("<td>Name</td>\r\n");
                    bw.write("<td>Rate</td>\r\n");
                    bw.write("<td>Price</td>\r\n");
                    bw.write("</th>\r\n");
                    bw.write("</thead>\r\n");
                    bw.write("<tbody>\r\n");

                    while (productsRS.next()) {
                        System.out.println("Name: " + productsRS.getString("name") + ", rate: " + productsRS.getDouble("rate") + " price: " + productsRS.getDouble("price"));
                        bw.write("<tr>\r\n");
                        bw.write("<td>\r\n");
                        bw.write("<td>" + productsRS.getString("name") + "<td>" + productsRS.getDouble("rate") + "<td>" + productsRS.getDouble("price") + "\r\n");
                        if (userLoggedIn) {
                            bw.write("<td><button><a href=\"order_" + productsRS.getInt("id") + "\">add to cart</a></button><br></td>" + "\r\n");
                        }
                        bw.write("</tr>\r\n");
                    }
                    bw.write("<tbody>\r\n");
                    bw.write("<table>\r\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            bw.write("<button> <a href=\"/\">BACK</a></button>\r\n");
            bw.write("</body>\r\n");
            bw.write("</html>\r\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCart() {
        try {
            bw.write("HTTP/1.1 200 OK\r\n");
            bw.write("\r\n");
            bw.write("<html>\r\n");
            bw.write("<body>\r\n");
            bw.write("<h2>user: " + this.userName + "</h2>\r\n");

            try (Statement stmt = DBHelper.connection.createStatement()) {
                ResultSet productsCountRS = stmt.executeQuery("select count(name) as count from cart");
                int productsCount = 0;
                if (productsCountRS.next()) {
                    productsCount = productsCountRS.getInt("count");
                }
                if (productsCount < 1) {
                    System.out.println(this.userName +"'s cart empty");
                    bw.write("<h3>cart empty</h3>\r\n");
                } else {
                    ResultSet productsRS = stmt.executeQuery("select * from cart join categories on cart.category_id = categories.id where user_id = " + userID);

                    bw.write("<h3>Cart</h3>\r\n");
                    bw.write("<table>\r\n");
                    bw.write("<thead>\r\n");
                    bw.write("<th>\r\n");
                    bw.write("<td>Category</td>\r\n");
                    bw.write("<td>Name</td>\r\n");
                    bw.write("<td>Rate</td>\r\n");
                    bw.write("<td>Price</td>\r\n");
                    bw.write("</th>\r\n");
                    bw.write("</thead>\r\n");
                    bw.write("<tbody>\r\n");

                    System.out.println("Products in " + this.userName + "'s cart: ");
                    while (productsRS.next()) {
                        System.out.println(productsRS.getString("categories.name") + " Name: " + productsRS.getString("name") + ", rate: " + productsRS.getDouble("rate") + " price: " + productsRS.getDouble("price"));
                        bw.write("<tr>\r\n");
                        bw.write("<td>\r\n");
                        bw.write("<td>" + productsRS.getString("categories.name") + "<td>" + productsRS.getString("name") + "<td>" + productsRS.getDouble("rate") + "<td>" + productsRS.getDouble("price") + "<td> <button><a href=\"remove_" + productsRS.getInt("id") + "\">remove</a></button><br></td>" + "\r\n");
                        bw.write("</tr>\r\n");
                    }
                    bw.write("<tbody>\r\n");
                    bw.write("<table>\r\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            bw.write("<button> <a href=\"/\">HOME</a></button>\r\n");
            bw.write("</body>\r\n");
            bw.write("</html>\r\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFromCart(int cartID) throws IOException {
        try (Statement stmt = DBHelper.connection.createStatement()) {
            ResultSet productRS = stmt.executeQuery("select * from cart where id = " + cartID);
            if (productRS.next()) {
                String name = productRS.getString("name");
                double rate = productRS.getDouble("rate");
                double price = productRS.getDouble("price");
                System.out.println("Product removed from cart: Name: " + name + ", Rate: " + rate + ", Price: " + price + "€.");

//                Store.getInstance().removeProductFromPurchasedProducts(name);
            }
            stmt.execute("delete from cart where id = " + cartID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        bw.write("HTTP/1.1 200 OK\r\n");
        bw.write("\r\n");
        bw.write("<html>\r\n");
        bw.write("<body>\r\n");
        bw.write("<h4>Product removed from cart</h4>\r\n");
        bw.write("<button> <a href=\"/cart\">BACK</a></button>\r\n");
        bw.write("<button> <a href=\"/\">HOME</a></button>\r\n");
        bw.write("</body>\r\n");
        bw.write("</html>\r\n");
        bw.flush();
    }

    public void createOrder(int productID) {
        List<Product> products = new ArrayList<>();
        try (Statement stmt = DBHelper.connection.createStatement()) {
            ResultSet productsRS = stmt.executeQuery("select * from products where id = " + productID);
            while (productsRS.next()) {
                System.out.println("product added to cart:");
                System.out.println("Name: " + productsRS.getString("name") + ", Rate: " + productsRS.getDouble("rate") + " Price: " + productsRS.getDouble("price") + "€.");
                PreparedStatement insertProductToCart = DBHelper.connection.prepareStatement("INSERT INTO cart(user_id, category_id, product_id, name, rate, price) VALUES(?, ?, ?, ?, ?, ?)");
                insertProductToCart.setInt(1, userID);
                insertProductToCart.setInt(2, productsRS.getInt("category_id"));
                insertProductToCart.setInt(3, productID);
                insertProductToCart.setString(4, productsRS.getString("name"));
                insertProductToCart.setDouble(5, productsRS.getDouble("rate"));
                insertProductToCart.setDouble(6, productsRS.getDouble("price"));
                insertProductToCart.execute();
                Product product = new Product.Builder()
                        .name(productsRS.getString("name"))
                        .rate(productsRS.getDouble("rate"))
                        .price(productsRS.getDouble("price"))
                        .build();
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        executorService.execute(new Order(products));
        try {
            bw.write("HTTP/1.1 200 OK\r\n");
            bw.write("\r\n");
            bw.write("<html>\r\n");
            bw.write("<body>\r\n");
            bw.write("<h4>Product added to cart</h4>\r\n");
            bw.write("<button> <a href=\"/\">HOME</a></button>\r\n");
            bw.write("<button> <a href=\"/cart\">CART</a></button>\r\n");
            bw.write("</body>\r\n");
            bw.write("</html>\r\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logInGET() {
        try {
            bw.write("HTTP/1.1 200 OK\r\n");
            bw.write("\r\n");
            bw.write("<html>\r\n");
            bw.write("<body>\r\n");
            bw.write("<form method=\"POST\">\r\n");
            bw.write("<input name=\"Username\"><br>\r\n");
            bw.write("<input name=\"Password\"><br>\r\n");
            bw.write("<input type=\"submit\" value=\"Log in\" href=\"/\">\r\n");
            bw.write("</form>\r\n");
            bw.write("<button> <a href=\"/\">HOME</a></button>\r\n");
            bw.write("</body>\r\n");
            bw.write("</html>\r\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logInPOST(String userName, String password) throws IOException {
        try (Statement stmt = DBHelper.connection.createStatement()) {
            ResultSet usersRS = stmt.executeQuery("select * from users where username= '" + userName + "' and hash = '" + password.hashCode() + "'");
            if (usersRS.next()) {
                if (userName.equalsIgnoreCase(usersRS.getString("username")) && password.hashCode() == usersRS.getInt("hash")) {
                    System.out.println("password hash: " + usersRS.getInt("hash"));
                    userID = usersRS.getInt("id");
                    userLoggedIn = true;
                    this.userName = usersRS.getString("username");
                    showIndex();
                }
            } else {
                try {
                    bw.write("HTTP/1.1 200 OK\r\n");
                    bw.write("\r\n");
                    bw.write("<html>\r\n");
                    bw.write("<body>\r\n");
                    bw.write("<h4>COULDN'T LOG IN</h4>\r\n");
                    bw.write("<button> <a href=\"/\">HOME</a></button>\r\n");
                    bw.write("</body>\r\n");
                    bw.write("</html>\r\n");
                    bw.flush();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void registerGET() {
        try {
            bw.write("HTTP/1.1 200 OK\r\n");
            bw.write("\r\n");
            bw.write("<html>\r\n");
            bw.write("<body>\r\n");
            bw.write("<form method=\"POST\">\r\n");
            bw.write("<input name=\"Username\"><br>\r\n");
            bw.write("<input name=\"Password\"><br>\r\n");
            bw.write("<input type=\"submit\" value=\"Register\" href=\"/\">\r\n");
            bw.write("</form>\r\n");
            bw.write("<button> <a href=\"/\">HOME</a></button>\r\n");
            bw.write("</body>\r\n");
            bw.write("</html>\r\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerPOST(String username, String password) throws IOException {
        try (Statement stmt = DBHelper.connection.createStatement()) {
            stmt.execute("insert into users(username, hash) values('" + username + "', '" + password.hashCode() + "')");
            logInPOST(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void logOut() throws IOException {
        userLoggedIn = false;
        userName = "";
        showIndex();
    }

    @Override
    public void createOrder() {
    }


    @Override
    public void fillStoreRandomly() {
        try {
            List<Category> categories = Store.getInstance().getCategoriesList();
            for (int i = 0; i < categories.size(); i++) {
                PreparedStatement insertCategories = DBHelper.connection.prepareStatement("INSERT INTO categories(id, name) VALUES(?, ?)");
                insertCategories.setInt(1, i + 1);
                insertCategories.setString(2, categories.get(i).getName().toString());
                insertCategories.execute();
                int randomProductAmountToAdd = faker.random().nextInt(3, 10);
                for (int j = 0; j < randomProductAmountToAdd; j++) {
                    PreparedStatement insertProduct = DBHelper.connection.prepareStatement("INSERT INTO products(category_id, name, rate, price) VALUES(?, ?, ?, ?)");
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
}