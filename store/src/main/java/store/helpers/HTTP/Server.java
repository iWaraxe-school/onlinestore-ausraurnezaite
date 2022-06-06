package store.helpers.HTTP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 9000;
    private static final String HTTP_VERSION = "HTTP/1.1";
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String QUIT = "/quit";
    private static final String LIST = "/list";
    private static final String SORT = "/sort";
    private static final String TOP = "/top";

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
//                        OutputStream os = socket.getOutputStream();
//                        Writer w = new OutputStreamWriter(os, "UTF-8");
//                        BufferedWriter bw = new BufferedWriter(w);
                ) {
                    String line = br.readLine();

                    if (line != null) {
                        System.out.println(line);
                        String[] parts = line.split(" ");
                        if (parts.length == 3 && parts[2].equals(HTTP_VERSION)) {
                            if (GET.equals(parts[0]) && QUIT.equals(parts[1])) {
                                httpHelper.quit();
                                serverRunning = false;

                            } else if (GET.equals(parts[0]) && "/".equals(parts[1])) {
                                httpHelper.showIndex();

                            } else if (SORT.equals(parts[1])) {
                                httpHelper.printSortedStore();

                            } else if (GET.equals(parts[0]) && LIST.equals(parts[1])) {
                                httpHelper.allProductsByPrice();
                            } else if (GET.equals(parts[0]) && TOP.equals(parts[1])) {
                                httpHelper.top5();
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