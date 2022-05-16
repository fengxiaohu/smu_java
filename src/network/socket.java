package network;

import  java.io.*;
import  java.net.*;

public class socket {
    public static void main(String args[])
    {
        ServerSocket echoServer = null;
        String line;
        DataInputStream inputStream;
        PrintStream outStream;
        Socket clientSocket = null;

        try
        {
            echoServer = new ServerSocket(8080);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        try
        {
            clientSocket = echoServer.accept();
            inputStream = new DataInputStream(clientSocket.getInputStream());
            outStream = new PrintStream(clientSocket.getOutputStream());
            while(true)
            {
                line = inputStream.readLine();
                outStream.println(line);

            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

}
