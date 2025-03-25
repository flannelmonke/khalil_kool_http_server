package com.khalil.httpserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerListenerThread extends Thread {

    private final static Logger LOGGER = Logger.getLogger(ServerListenerThread.class.getName());

    private final int port;
    private final String webroot;
    private final ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {

        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                LOGGER.info(" * Connection Accepted " + socket.getInetAddress());

                HttpConnectionWorkerThread workerThread = new HttpConnectionWorkerThread(socket);
                workerThread.start();
                // serverSocket.close(); // TODO handle close.
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem with setting socket", e);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}
