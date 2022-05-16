package network;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import java.util.Map;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) {
        try {
            StringBuilder responseText = new StringBuilder();
            responseText.append("request method ").append(httpExchange.getRequestMethod()).append("<br/>");
            responseText.append("request parameter ").append(getRequestPara(httpExchange)).append("<br/>");
            responseText.append("request header: <br/>").append(getRequestHeader(httpExchange));
        } catch (Exception e) {
        e.printStackTrace();
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
                requestBodyContent.append(line);
            }
            param = requestBodyContent.toString();
        }
        return param;
    }
    private void handleResponse(HttpExchange httpExchange,String responseText) throws Exception
    {
        StringBuilder responseContent = new StringBuilder();
        responseContent.append("<html>").append("<body>").append(responseText).append("<>body").append("</html>");
        String responseContentString = responseContent.toString();
        byte[] responseContentByte = responseContentString.getBytes(StandardCharsets.UTF_8);

        httpExchange.getRequestHeaders().add("Content-type","text/html;charset = utf-8");
        httpExchange.sendResponseHeaders(200,responseContentByte.length);
        OutputStream out = httpExchange.getResponseBody();
        out.write(responseContentByte);
        out.flush();
        out.close();
    }



}
