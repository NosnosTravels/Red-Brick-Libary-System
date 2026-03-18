/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.sun.net.httpserver.HttpServer;
import http.HttpService;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author M2200478
 */
public class HttpTesting {

    private static final int PORT = 8081;
    private static HttpServer server;
    private static HttpResponse response;

    public HttpTesting() {
    }

    @BeforeAll
    public static void setUpClass() throws IOException {
        // Create connection to db
        //start server

        try {
            URI uri = URI.create("http://localhost:" + PORT + "/home");
            HttpService.startServer(PORT);
            HttpClient client = HttpClient.newHttpClient();
            Thread.sleep(300);
            response = client.send(
                    HttpRequest.newBuilder().GET().uri(uri).build(),
                    BodyHandlers.ofString()
            );

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SERVER FAILED TO START");
            e.getMessage();
        }
    }

    @AfterAll
    public static void tearDownClass() {
        // remove any newly created data
        //write something to close the db connection (should be handled using try with resources)
        try {
            server.stop(0);
        } catch (Exception e) {
            e.getMessage();
        }

    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void StartServerOnPort8081() {
        assertNotNull(response, "Response should not be Null");
        assertEquals(200, response.statusCode());
    }
    //create method for testing

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
