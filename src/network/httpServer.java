package network;
import  com.sun.net.httpserver.Headers;
import  com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class httpServer {
    public static void main(String[] args) throws IOException
    {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080),0);
        httpServer.createContext("/mypage",new MyHttpHandler());
        httpServer.setExecutor(Executors.newFixedThreadPool(10));
        httpServer.start();
    }
}
