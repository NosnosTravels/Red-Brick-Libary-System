/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author M2200478
 */
public class StaticFileHandler implements HttpHandler {

    private final String root;

    public StaticFileHandler(String root) {
        this.root = root;

    }


    public void handle(HttpExchange exchange) throws IOException {
        String uri = exchange.getRequestURI().getPath();
        String fileName = uri.substring("/js".length());
        Path path = Paths.get(root, fileName);

        if (!Files.exists(path)|| Files.isDirectory(path)) {
            String response = "404 file not found";
            exchange.sendResponseHeaders(404, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
            return;
        }
        byte[] file = Files.readAllBytes(path);
        exchange.getResponseHeaders().set("Content-Type", getMimeType(path));
        exchange.sendResponseHeaders(200, file.length);
        OutputStream os = exchange.getResponseBody();
        os.write(file);
        os.close();
    }

    private String getMimeType(Path path) {
        String name = path.toString().toLowerCase();

        if (name.endsWith(".html")) {
            return "text/html";
        }
        if (name.endsWith(".js")) {
            return "application/javascript";
        }
        if (name.endsWith(".css")) {
            return "text/css";
        }
        if (name.endsWith(".json")) {
            return "application/json";
        }
        if (name.endsWith(".png")) {
            return "image/png";
        }
        if (name.endsWith(".jpg") || name.endsWith(".jpeg")) {
            return "image/jpeg";
        }

        return "application/octet-stream";
    }

}
