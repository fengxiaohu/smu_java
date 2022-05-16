package network;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.org.apache.xpath.internal.operations.String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import java.util.Map;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) {
        try {
            StringBuilder responseText = new StringBuilder();
            responseText.append("request method ").append(httpExchange.getRequestMethod().).append("<br/>");
            responseText.append("request parameter ").append();
            responseText.append("request header: <br/>").append(getRequestHeader(httpExchange));
        } catch (Exception e) {

        }
    }
    private String getRequestHeader(HttpExchange httpExchange)
    {
        Headers headers = httpExchange.getRequestHeaders();
        String requestHeaders = headers.entrySet().stream().map((Map.Entry<String,List<String>> entry) -> entry.getKey() + ":" +
                entry.getValue().toString()).collect(Collectors.joining("<br/>"));
        System.out.println(requestHeaders);
        return requestHeaders;
    }
    private String  getRequestPara(HttpExchange httpExchange)throws Exception{
        String param = "";
        if(httpExchange.getRequestMethod().equals("GET"))
        {
            param = httpExchange.getRequestURI().getQuery();

        }
        else
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(),"utf-8"));
            StringBuilder requestBodyContent = new StringBuilder();
            String line = null;
            while((line = bufferedReader.readLine())!=null)
            {

            }

        }
    }
}
