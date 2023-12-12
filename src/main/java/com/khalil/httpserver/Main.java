package com.khalil.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.khalil.httpserver.Config.Configuration;
import com.khalil.httpserver.Config.configurationManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Server Starting...");

        configurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = configurationManager.getInstance().getCurrentConfiguration();

        System.out.println("USING PORT: "+conf.getPort());
        System.out.println("USING WEBROOT:"+conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept(); //Returns a Socket && prompts socket to listen to a port and accept any connection


            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //TODO we would read (disregarded for now)

            //writing to browser
            String html = "<html><head><title>Simple Java Http server</title></head><body><h1>This page was server using my Simple Java Http Server</h1></body></html>";

            final String CRLF = "\n\r"; // 13, 10 in ASCII

            String response = 
                "HTTP/1.1 200 OK" + CRLF +//Status line : HTTP Version RESPONSE_CODE RESPONSE_MESSAGE
                "Content-Length:" + html.getBytes().length + CRLF +  // HEADER 
                    CRLF + 
                    html + 
                    CRLF + CRLF;

            outputStream.write(response.getBytes());
            
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}