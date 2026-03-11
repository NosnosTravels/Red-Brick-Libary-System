/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book;

import Exceptions.IllegalArugmentException;
import static ch.qos.logback.core.joran.spi.HttpUtil.RequestMethod.POST;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.invoke.VarHandle.AccessMode.GET;
import java.nio.charset.StandardCharsets;
import java.util.List;
import repositories.BookService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import book.ISBN;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author M2200478
 */
public class BookHandler implements HttpHandler {

    private final BookService bookService;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public BookHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void handle(HttpExchange ex) throws IOException {
        String method = ex.getRequestMethod();
        String path = ex.getRequestURI().getPath();
        ex.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");

        if (path.startsWith("/books/") && path.length() > "/books/".length()) {
            try {
                String ISBNStr = path.substring("/books/".length());
                ISBN Isbn = new ISBN(ISBNStr);
                if (GET.name().equals(method)) {
                    List<Book> matches = bookService.findByIsbn(Isbn);
                    SendJson(ex, 200, gson.toJson(matches));
                    return;
                }
                SendJson(ex, 405, "{\"error\":\"Method Not Allowed\"}");
            } catch (IllegalArugmentException ex1) {
                Logger.getLogger(BookHandler.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        if (GET.name().equals(method)) {
            List<Book> books = bookService.findAll();
            SendJson(ex, 200, gson.toJson(books));
            return;
        }
        if (POST.name().equals(method)) {
            String body = readAll(ex.getRequestBody());
            BookIn dto = gson.fromJson(body, BookIn.class);
            if (dto == null || dto.title == null || dto.author == null || dto.format == null) {
                SendJson(ex, 400, "{\"error\":\"invaldid body\"}");
            }
            Book created = bookService.create(dto.Isbn, dto.title, dto.author, dto.format);
            SendJson(ex, 201, gson.toJson(created));
            return;
        }
        SendJson(ex, 405, "{\"error\":\"Method Not Allowed\"}");
    }

    private static String readAll(InputStream in) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int r;
        while ((r = in.read(buf)) != -1) {
            bos.write(buf, 0, r);
        }
        return bos.toString(StandardCharsets.UTF_8);
    }

    private static void SendJson(HttpExchange ex, int status, String json) throws IOException {
        byte[] out = json.getBytes(StandardCharsets.UTF_8);
// TODO: Set CORS headers and remove * catch all origin​
        ex.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        ex.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        ex.sendResponseHeaders(status, out.length);
        ex.getResponseBody().write(out);
        ex.getResponseBody().close();
        //converting the data into json and setting the headers
    }

    private static class BookIn {

        ISBN Isbn;
        String title;
        String author;
        String format;
        String State;

    }
    
 
    
    

}