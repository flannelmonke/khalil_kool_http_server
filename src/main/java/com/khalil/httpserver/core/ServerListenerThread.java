package com.khalil.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerListenerThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) { // isBound() returns true if the socket is
                                                                         // bound to
                                                                         // an address
                                                                         // isClosed() returns true if the socket has
                                                                         // been
                                                                         // closed
                Socket socket = serverSocket.accept(); // Returns a Socket && prompts socket to listen to a port and
                                                       // accept
                                                       // any connection

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                LOGGER.info("Connection accepted from " + socket.getInetAddress());
                // TODO we would read (disregarded for now)

                // writing to browser
                String html = "<html><head><title>Simple Java Http server</title></head><body><h1>This page was server using my Simple Java Http Server</h1></body></html>";

                final String CRLF = "\n\r"; // 13, 10 in ASCII

                String response = "HTTP/1.1 200 OK" + CRLF + // Status line : HTTP Version RESPONSE_CODE
                                                             // RESPONSE_MESSAGE
                        "Content-Length:" + html.getBytes().length + CRLF + // HEADER
                        CRLF +
                        html +
                        CRLF + CRLF;

                outputStream.write(response.getBytes());

                inputStream.close();
                outputStream.close();
                socket.close();
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
