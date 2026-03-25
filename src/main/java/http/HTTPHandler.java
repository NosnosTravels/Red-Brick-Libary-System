/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package http;

import static ch.qos.logback.core.joran.spi.HttpUtil.RequestMethod.POST;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.invoke.VarHandle.AccessMode.GET;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 *
 * @author M2200478
 */
public class HTTPHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equalsIgnoreCase(exchange.getRequestMethod())){
            Path htmlPath = Paths.get("src/html/book.html");
//            Path JSPath = Paths.get("src/html/js");
            if (Files.exists(htmlPath)){
                byte[] htmlBytes = Files.readAllBytes(htmlPath);
                exchange.sendResponseHeaders(200, htmlBytes.length);
                exchange.getResponseBody().write(htmlBytes);
                exchange.getResponseBody().close();             
            } else {
                exchange.sendResponseHeaders(404, -1);
            }
        } else {
            exchange.sendResponseHeaders(405, -1);
        }

        SendJson(exchange, 405, "{\"error\":\"Method Not Allowed\"}");  
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

    private void handleGet(HttpExchange exchange) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void handlePOST(HttpExchange exchange) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
    //        
//        
//        String method = exchange.getRequestMethod();
//        if (GET.equals(method)) {
//            handleGet(exchange);
//            return;
//        } else if (POST.equals(method)) {
//            handlePOST(exchange);
//            return;
//        }
}


