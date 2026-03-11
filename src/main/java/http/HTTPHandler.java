/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package http;

import static ch.qos.logback.core.joran.spi.HttpUtil.RequestMethod.POST;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.invoke.VarHandle.AccessMode.GET;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author M2200478
 */
public class HTTPHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "<h1>Hello, World</h1>"
                         +"<p>This is my Libary System</p>";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
        
        String method = exchange.getRequestMethod();
        if (GET.equals(method)) {
            handleGet(exchange);
            return;
        } else if (POST.equals(method)) {
            handlePOST(exchange);
            return;
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

    
}
