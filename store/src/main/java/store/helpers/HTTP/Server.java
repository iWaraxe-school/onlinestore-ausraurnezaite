package store.helpers.HTTP;

import store.helpers.DBHelper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {

    private static final int PORT = 9000;
    private static final String HTTP_VERSION = "HTTP/1.1";
    private static final String FAVICON = "/favicon.ico";
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String QUIT = "/quit";
    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    private static final String LOG_OUT = "/logout";
    private static final String LIST = "/list";
    private static final String SORT = "/sort";
    private static final String TOP = "/top";
    private static final String CART = "/cart";
    private static final String ORDER = "/order";
    private static final String REMOVE = "/remove";

    public static void startServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);

            boolean serverRunning = true;
            while (serverRunning) {
                Socket socket = serverSocket.accept();
                HTTPHelper httpHelper = new HTTPHelper(socket);


                try (
                        InputStream is = socket.getInputStream();
                        Reader r = new InputStreamReader(is, "UTF-8");
                        BufferedReader br = new BufferedReader(r);

                ) {
                    String line = br.readLine();

                    if (line != null) {

                        String[] parts = line.split(" ");
                        if (!FAVICON.equals(parts[1])) {
                            System.out.println(line);
                        }
                        if (parts.length == 3 && parts[2].equals(HTTP_VERSION)) {


                            if (GET.equals(parts[0]) && QUIT.equals(parts[1])) {
                                httpHelper.quit();
                                socket.close();
                                serverRunning = false;

                            } else if ("/".equals(parts[1])) {
                                httpHelper.showIndex();

                            } else if (SORT.equals(parts[1])) {
                                httpHelper.printSortedStore();

                            } else if (LIST.equals(parts[1])) {
                                httpHelper.allProductsByPrice();

                            } else if (TOP.equals(parts[1])) {
                                httpHelper.top5();

                            } else if (CART.equals(parts[1])) {
                                httpHelper.showCart();

                            } else if (parts[1].contains(ORDER)) {
                                System.out.println(parts[1]);
                                String[] order = parts[1].split("_");
                                int productID = Integer.parseInt(order[1]);
                                System.out.println(productID);
                                httpHelper.createOrder(productID);

                            } else if (parts[1].contains(REMOVE)) {
//                                System.out.println(parts[1]);
                                String[] order = parts[1].split("_");
                                int cartID = Integer.parseInt(order[1]);
//                                System.out.println(cartID);
                                httpHelper.removeFromCart((cartID));

                            } else if (GET.equals(parts[0]) && LOGIN.equals(parts[1])) {
                                httpHelper.logInGET();

                            } else if (POST.equals(parts[0]) && LOGIN.equals(parts[1])) {
                                //read headers
                                String headerLine = null;
                                while ((headerLine = br.readLine()).length() != 0) {
                                    System.out.println(headerLine);
                                }
                                //read the post payload data
                                StringBuilder payload = new StringBuilder();
                                while (br.ready()) {
                                    payload.append((char) br.read());
                                }
                                System.out.println("Payload data is: " + payload.toString());
                                String[] payloadParts = payload.toString().split("&");
                                String[] usernamePair = payloadParts[0].split("=");
                                String[] passwordPair = payloadParts[1].split("=");
                                String username = usernamePair[1];
                                String password = passwordPair[1];
                                httpHelper.logInPOST(username, password);
                            } else if (LOG_OUT.equals(parts[1])) {
                                httpHelper.logOut();
                            } else if (GET.equals(parts[0]) && REGISTER.equals(parts[1])) {
                                httpHelper.registerGET();
                            } else if (POST.equals(parts[0]) && REGISTER.equals(parts[1])) {
                                //read headers
                                String headerLine = null;
                                while ((headerLine = br.readLine()).length() != 0) {
//                                    System.out.println(headerLine);
                                }
                                //read the post payload data
                                StringBuilder payload = new StringBuilder();
                                while (br.ready()) {
                                    payload.append((char) br.read());
                                }
                                System.out.println("Payload data is: " + payload.toString());
                                String[] payloadParts = payload.toString().split("&");
                                String[] usernamePair = payloadParts[0].split("=");
                                String[] passwordPair = payloadParts[1].split("=");
                                String username = usernamePair[1];
                                String password = passwordPair[1];
                                httpHelper.registerPOST(username, password);
                            }
                        } else {
                            System.err.println("Non-HTTP request");
                        }
                    } else {
                        System.err.println("Empty request");
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Failed to start server on port " + PORT);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (Exception ex) {
                    // ignore
                }
            }
        }
    }
}