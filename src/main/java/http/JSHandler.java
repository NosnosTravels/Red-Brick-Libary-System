/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author M2200478
 */
public class JSHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        
        
        if ("GET".equalsIgnoreCase(exchange.getRequestMethod())){
            Path JSPath = Paths.get("src/html/js/loadBooksBtn.js");
            if (Files.exists(JSPath)){ 
                byte[] JSBytes = Files.readAllBytes(JSPath);
                exchange.getResponseHeaders().set("Content-Type", "text/javascript");
                exchange.sendResponseHeaders(200, JSBytes.length);
            } else {
                exchange.sendResponseHeaders(404, -1);
            }
        } else {
            exchange.sendResponseHeaders(405, -1);
        }      
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