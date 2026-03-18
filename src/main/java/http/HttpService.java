/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package http;

import Database.ConnectionImpl;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import repositories.BookRepositoryInterface;
import repositories.BookRepositoryJdbc;
import repositories.BookService;
import http.JSHandler;

/**
 *
 * @author M2200478
 */
public class HttpService {

    private static HttpServer server;

    public static void startServer(int port) throws IOException, SQLException {
        BookRepositoryInterface BookService;
        BookService = (BookRepositoryInterface) new BookService();

//        BookService bookService;
//        bookService = new BookService(bookRepository);
        ConnectionImpl.conn();
        server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Server started at http://localhost:" + port + "/books");
        registerEndPoints();
        server.setExecutor(null);
        server.start();

    }

    public static void stopServer() {
        server.stop(0);
    }

    private static void registerEndPoints() {
        server.createContext("/", new HTTPHandler());
        server.createContext("/books", new HTTPHandler());
        server.createContext("/books/", new HTTPHandler());
        server.createContext("/loadBooksBtn", new JSHandler());
    }
    
    private static void SendJson(HttpExchange ex, int status, String json) throws IOException {
        byte[] out = json.getBytes(StandardCharsets.UTF_8);
// TODO: Set CORS headers and remove * catch all origin​
        ex.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        ex.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        ex.sendResponseHeaders(status, out.length);
        ex.getResponseBody().write(out);
        ex.getResponseBody().close();
    }

}
